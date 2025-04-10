package com.example.CRB.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains DTOs for JSON communication with clients
 */
@Schema(description = "CRB DTOs")
public class Crbdto {

    /**
     * Request DTO for CRB check (JSON)
     * This class represents the structure of the JSON request from clients
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JacksonXmlRootElement(localName = "Request")
    @Schema(description = "Request for checking if a user is in CRB")
    public static class Request {
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

    /**
     * Response DTO for CRB check (JSON)
     * This class represents the structure of the JSON response to clients
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Response containing CRB check result")
    public static class Response {
        @Schema(description = "Status code (200 for success, 500 for error)", example = "200")
        private int status;

        @Schema(description = "Status message", example = "User found in CRB")
        private String message;

        @Schema(description = "User data (null if not found)")
        private UserData data;

        /**
         * Static factory method for creating success responses
         */
        public static Response success(UserData userData) {
            return Response.builder()
                    .status(200)
                    .message("User found in CRB")
                    .data(userData)
                    .build();
        }

        /**
         * Static factory method for creating error responses
         */
        public static Response error() {
            return Response.builder()
                    .status(500)
                    .message("User not found in CRB")
                    .data(null)
                    .build();
        }
    }

    /**
     * User data contained in response (JSON)
     * This class represents the user data returned to clients
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "User data from CRB")
    public static class UserData {
        @Schema(description = "User's salutation", example = "Mr")
        private String salutation;

        @Schema(description = "User's full name", example = "John Doe Smith")
        private String fullName;

        @Schema(description = "User's other name", example = "Johnny")
        private String otherName;

        @Schema(description = "User's telephone number", example = "1234567890")
        private String phoneNo;
    }
}