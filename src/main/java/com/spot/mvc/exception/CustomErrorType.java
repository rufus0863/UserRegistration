package com.spot.mvc.exception;

import com.spot.mvc.dto.UserDTO;

public class CustomErrorType extends UserDTO {

    private String errorMessage;

    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
