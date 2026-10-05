package com.nqd.qunhlanmakeupbe.service.admin;

import com.nqd.qunhlanmakeupbe.common.dto.PageResponse;
import com.nqd.qunhlanmakeupbe.dto.request.UserCreateRequest;
import com.nqd.qunhlanmakeupbe.dto.request.UserUpdateRequest;
import com.nqd.qunhlanmakeupbe.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface AdminUserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUserById(UUID id);

    List<UserResponse> getAllUsers();

    PageResponse<UserResponse> getUsersPaged(int page, int size, String sortBy, String sortDirection);

    UserResponse updateUser(UUID id, UserUpdateRequest request);

    void deleteUser(UUID id);
}
