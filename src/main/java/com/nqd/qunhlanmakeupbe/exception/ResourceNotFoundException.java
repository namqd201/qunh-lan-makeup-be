package com.nqd.qunhlanmakeupbe.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends AppException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(ErrorCode.RESOURCE_NOT_FOUND, String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
