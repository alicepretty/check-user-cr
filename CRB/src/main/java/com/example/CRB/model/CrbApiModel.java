package com.example.CRB.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Models for XML communication with the CRB API
 */
public class CrbApiModel {

    /**
     * Request model for CRB API (XML)
     * This class represents the structure of the XML request that will be sent to the CRB API
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JacksonXmlRootElement(localName = "CrbRequest")
    public static class Request {
        @JacksonXmlProperty(localName = "FirstName")
        private String firstName;

        @JacksonXmlProperty(localName = "LastName")
        private String lastName;

        @JacksonXmlProperty(localName = "Surname")
        private String surname;

        @JacksonXmlProperty(localName = "NationalId")
        private String nationalId;

        @JacksonXmlProperty(localName = "Passport")
        private String passport;

        @JacksonXmlProperty(localName = "CompanyRegistration")
        private String companyRegistration;
    }

    /**
     * Response model from CRB API (XML)
     * This class represents the structure of the XML response that will be received from the CRB API
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JacksonXmlRootElement(localName = "CrbResponse")
    public static class Response {
        @JacksonXmlProperty(localName = "Status")
        private String status;
        @JacksonXmlProperty(localName = "FullName")
        private String fullName;

        @JacksonXmlProperty(localName = "salutation")
        private String salutation;

        @JacksonXmlProperty(localName = "OtherName")
        private String otherName;

        @JacksonXmlProperty(localName = "TelephoneNumber")
        private String phoneNo;

        @JacksonXmlProperty(localName = "NationalID")
        private String nationalID;

        @JacksonXmlProperty(localName = "passportNo")
        private String passportNo;

        @JacksonXmlProperty(localName = "ServiceID")
        private String serviceID;

        @JacksonXmlProperty(localName = "Designation")
        private String designation;

        @JacksonXmlProperty(localName = "Designation")
        private String crbName;
    }
}