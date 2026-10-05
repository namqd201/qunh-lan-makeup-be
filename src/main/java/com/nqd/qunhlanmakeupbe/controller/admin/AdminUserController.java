package com.nqd.qunhlanmakeupbe.controller.admin;

import com.nqd.qunhlanmakeupbe.common.constant.AppConstants;
import com.nqd.qunhlanmakeupbe.common.dto.ApiResponse;
import com.nqd.qunhlanmakeupbe.common.dto.PageResponse;
import com.nqd.qunhlanmakeupbe.dto.request.UserCreateRequest;
import com.nqd.qunhlanmakeupbe.dto.request.UserUpdateRequest;
import com.nqd.qunhlanmakeupbe.dto.response.UserResponse;
import com.nqd.qunhlanmakeupbe.service.admin.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
@Tag(name = "Admin - User Management", description = "Admin APIs for managing users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Admin: Create a new user")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.success("User created successfully", adminUserService.createUser(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Admin: Get user details by ID")
    public ApiResponse<UserResponse> getUserById(@PathVariable UUID id) {
        return ApiResponse.success(adminUserService.getUserById(id));
    }

    @GetMapping
    @Operation(summary = "Admin: Get paginated user list")
    public ApiResponse<PageResponse<UserResponse>> getUsers(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDirection
    ) {
        return ApiResponse.success(adminUserService.getUsersPaged(page, size, sortBy, sortDirection));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Admin: Update user by ID")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return ApiResponse.success("User updated successfully", adminUserService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Admin: Delete user by ID")
    public ApiResponse<Void> deleteUser(@PathVariable UUID id) {
        adminUserService.deleteUser(id);
        return ApiResponse.success("User deleted successfully", null);
    }
}
