package com.nqd.qunhlanmakeupbe.mapper;

import com.nqd.qunhlanmakeupbe.dto.request.UserCreateRequest;
import com.nqd.qunhlanmakeupbe.dto.response.UserResponse;
import com.nqd.qunhlanmakeupbe.entity.Role;
import com.nqd.qunhlanmakeupbe.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .avatarUrl(request.getAvatarUrl())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole() != null ? request.getRole() : Role.CUSTOMER)
                .build();
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) {
            return null;
        }

        return UserResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .avatarUrl(entity.getAvatarUrl())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
