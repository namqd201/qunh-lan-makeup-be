package com.nqd.qunhlanmakeupbe.dto.response;

import com.nqd.qunhlanmakeupbe.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String email;

    private String fullName;

    private String avatarUrl;

    private String phoneNumber;

    private Role role;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
