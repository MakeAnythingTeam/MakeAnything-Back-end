package com.example.MakeAnything.domain.common;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private boolean success;

    private T data;

    private ErrorDTO error;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<T>(false , null, ErrorDTO.of(errorCode));
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
        return new ApiResponse<T>(false , null, ErrorDTO.of(errorCode, message));
    }
}
