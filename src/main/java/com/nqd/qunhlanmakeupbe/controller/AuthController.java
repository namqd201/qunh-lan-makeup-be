package com.nqd.qunhlanmakeupbe.controller;

import com.nqd.qunhlanmakeupbe.common.dto.ApiResponse;
import com.nqd.qunhlanmakeupbe.dto.request.GoogleLoginRequest;
import com.nqd.qunhlanmakeupbe.dto.response.AuthResponse;
import com.nqd.qunhlanmakeupbe.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google")
    @Operation(summary = "Sign in or sign up with Google ID token")
    public ApiResponse<AuthResponse> loginWithGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        return ApiResponse.success("Login successfully", authService.loginWithGoogle(request));
    }
}
