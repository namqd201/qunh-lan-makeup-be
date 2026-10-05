package com.nqd.qunhlanmakeupbe.dto.request;

import com.nqd.qunhlanmakeupbe.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String fullName;

    private String avatarUrl;

    private String phoneNumber;

    private Role role;
}
