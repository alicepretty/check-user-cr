package com.example.CRB.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request for checking if a user is in CRB")
public  class CrbRequest {
    @Schema(description = "CRB API username", example = "WS_Access1", required = true)
    private String username;

    @Schema(description = "CRB API password", example = "qwZcgj", required = true)
    private String password;

    @Schema(description = "CRB API code", example = "1607", required = true)
    private String code;

    @Schema(description = "CRB API infinity code", example = "3010RW19783", required = true)
    private String infinityCode;

    @Schema(description = "User's first name", example = "Serge", required = true)
    @NotBlank(message = "First name is required")
    private String name1;

    @Schema(description = "User's last name", example = "Karim", required = true)
    @NotBlank(message = "Last name is required")
    private String name2;

    @Schema(description = "User's third name (optional)", example = "")
    private String name3;

    @Schema(description = "User's fourth name (optional)", example = "")
    private String name4;

    @Schema(description = "User's national ID", example = "31995801992820", required = true)
    private String nationalID;

    @Schema(description = "User's passport number (optional)", example = "")
    private String passportNo;

    @Schema(description = "Service ID (optional)", example = "")
    private String serviceID;

    @Schema(description = "Alien ID (optional)", example = "")
    private String alienID;

    @Schema(description = "Tax ID (optional)", example = "")
    private String taxID;

    @Schema(description = "Date of birth (optional)", example = "")
    private String dateOfBirth;

    @Schema(description = "Postal box number (optional)", example = "")
    private String postalBoxNo;

    @Schema(description = "Postal town (optional)", example = "")
    private String postalTown;

    @Schema(description = "Postal country (optional)", example = "")
    private String postalCountry;

    @Schema(description = "Work telephone (optional)", example = "")
    private String telephoneWork;

    @Schema(description = "Home telephone (optional)", example = "")
    private String telephoneHome;

    @Schema(description = "Mobile telephone (optional)", example = "")
    private String telephoneMobile;

    @Schema(description = "Physical address (optional)", example = "")
    private String physicalAddress;

    @Schema(description = "Physical town (optional)", example = "")
    private String physicalTown;

    @Schema(description = "Physical country (optional)", example = "")
    private String physicalCountry;

    @Schema(description = "Report sector code", example = "2")
    private String reportSector;

    @Schema(description = "Report reason code", example = "1")
    private String reportReason;
    @Schema(description = "Format of the request and response (xml or json)", example = "xml")
    private String format;
}
