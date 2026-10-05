package com.nqd.qunhlanmakeupbe.service.impl;

import com.nqd.qunhlanmakeupbe.common.security.JwtService;
import com.nqd.qunhlanmakeupbe.dto.request.GoogleLoginRequest;
import com.nqd.qunhlanmakeupbe.dto.response.AuthResponse;
import com.nqd.qunhlanmakeupbe.dto.response.GoogleUserInfoDto;
import com.nqd.qunhlanmakeupbe.entity.Role;
import com.nqd.qunhlanmakeupbe.entity.User;
import com.nqd.qunhlanmakeupbe.exception.AppException;
import com.nqd.qunhlanmakeupbe.exception.ErrorCode;
import com.nqd.qunhlanmakeupbe.mapper.UserMapper;
import com.nqd.qunhlanmakeupbe.repository.UserRepository;
import com.nqd.qunhlanmakeupbe.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Value("${app.google.api-key:}")
    private String googleApiKey;

    @Value("${app.jwt.expiration-ms:86400000}")
    private long jwtExpirationMs;

    private final RestClient restClient = RestClient.create();

    @Override
    @Transactional
    public AuthResponse loginWithGoogle(GoogleLoginRequest request) {
        GoogleUserInfoDto googleUser = verifyGoogleToken(request.getIdToken());

        if (googleUser == null || googleUser.getEmail() == null || googleUser.getEmail().isBlank()) {
            throw new AppException(ErrorCode.INVALID_GOOGLE_TOKEN);
        }

        User user = userRepository.findByEmail(googleUser.getEmail())
                .map(existingUser -> {
                    if (Boolean.FALSE.equals(existingUser.getIsActive()) || Boolean.TRUE.equals(existingUser.getIsDeleted())) {
                        throw new AppException(ErrorCode.ACCOUNT_LOCKED);
                    }
                    if (googleUser.getName() != null && !googleUser.getName().isBlank()) {
                        existingUser.setFullName(googleUser.getName());
                    }
                    if (googleUser.getPicture() != null && !googleUser.getPicture().isBlank()) {
                        existingUser.setAvatarUrl(googleUser.getPicture());
                    }
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(googleUser.getEmail())
                            .fullName(googleUser.getName())
                            .avatarUrl(googleUser.getPicture())
                            .role(Role.CUSTOMER)
                            .build();
                    return userRepository.save(newUser);
                });

        String token = jwtService.generateToken(user.getId(), user.getEmail(), user.getRole());

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(jwtExpirationMs)
                .user(userMapper.toResponse(user))
                .build();
    }

    private GoogleUserInfoDto verifyGoogleToken(String idToken) {
        try {
            return restClient.get()
                    .uri("https://oauth2.googleapis.com/tokeninfo?id_token={idToken}", idToken)
                    .retrieve()
                    .body(GoogleUserInfoDto.class);
        } catch (Exception ex) {
            log.error("Failed to verify Google token: {}", ex.getMessage());
            throw new AppException(ErrorCode.INVALID_GOOGLE_TOKEN);
        }
    }
}
