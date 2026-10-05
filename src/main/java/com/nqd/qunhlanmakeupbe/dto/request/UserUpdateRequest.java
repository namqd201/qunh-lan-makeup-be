package com.nqd.qunhlanmakeupbe.dto.request;

import com.nqd.qunhlanmakeupbe.entity.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String fullName;

    private String avatarUrl;

    private String phoneNumber;

    private Role role;

    private Boolean isActive;
}
