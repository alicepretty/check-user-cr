package com.example.CRB.model;

import com.example.CRB.dto.Crbdto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public  class CrbResponse {
    @Schema(description = "Status code (200 for success, 500 for error)", example = "200")
    private int status;

    @Schema(description = "Status message", example = "User found in CRB")
    private String message;

    @Schema(description = "User data (null if not found)")
    private CrbResponseData data;

    /**
     * Static factory method for creating success responses
     */
    public static Crbdto.Response success(Crbdto.UserData userData) {
        return Crbdto.Response.builder().status(200).message("User found in CRB").data(userData).build();
    }

    /**
     * Static factory method for creating error responses
     */
    public static Crbdto.Response error(String message, int statusCode) {
        return Crbdto.Response.builder()
                .status(statusCode)
                .message(message)
                .data(null)
                .build();    }
}

