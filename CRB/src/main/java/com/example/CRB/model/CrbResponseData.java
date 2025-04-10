package com.example.CRB.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CrbResponseData {
    @Schema(description = "User's salutation", example = "Mr")
    private String salutation;

    @Schema(description = "User's full name", example = "John Doe Smith")
    private String fullName;

    @Schema(description = "User's other name", example = "Johnny")
    private String otherName;

    @Schema(description = "User's telephone number", example = "1234567890")
    private String phoneNo;

    @Schema(description = "User's passport", example = "1234567890")
    private String passportNo;

    @Schema(description = " user's nationalID", example = "1234567890")
    private String nationalID;

    @Schema(description = " user's service id", example = "1234")
    private String serviceID;

    @Schema(description = " user's service id", example = "1234")
    private String designation;

    @Schema(description = " user's service id", example = "1234")
    private String crbName;
}

