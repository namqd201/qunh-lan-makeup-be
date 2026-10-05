package com.nqd.qunhlanmakeupbe.service;

import com.nqd.qunhlanmakeupbe.dto.request.GoogleLoginRequest;
import com.nqd.qunhlanmakeupbe.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse loginWithGoogle(GoogleLoginRequest request);
}
