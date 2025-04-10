package com.example.CRB.service.map;

import com.example.CRB.dto.Crbdto;
import com.example.CRB.model.CrbRequest;
import com.example.CRB.model.CrbResponse;
import com.example.CRB.model.CrbResponseData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CrbMap {
    public static boolean isValidRwandaNationalId(String nationalId) {
        if (nationalId == null || nationalId.isEmpty()) {
            return false;
        }

        // Remove any non-alphanumeric characters
        String cleanId = nationalId.replaceAll("[^a-zA-Z0-9]", "");

        // Check if the ID has the correct length (16 characters)
        if (cleanId.length() != 16) {
            return false;
        }

        return true;
    }

    public static String mapCreateSoapEnvelope(CrbRequest request) {
        String xml = "" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.rw.crbws.transunion.ke.co/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ws:getProduct123>\n" +
                "         <username>WS_Access1</username>\n" +
                "         <password>qwZcgj</password>\n" +
                "         <code>1607</code>\n" +
                "         <infinityCode>3010RW19783</infinityCode>\n" +
                "         <name1>" + request.getName1() + "</name1>\n" +
                "         <name2>" + request.getName2() + "</name2>\n" +
                "         <name3></name3>\n" +
                "         <name4></name4>\n" +
                "         <nationalID>" + request.getNationalID() + "</nationalID>\n" +
                "         <passportNo>" + request.getPassportNo() + "</passportNo>\n" +
                "         <serviceID></serviceID>\n" +
                "         <alienID></alienID>\n" +
                "         <taxID></taxID>\n" +
                "         <dateOfBirth></dateOfBirth>\n" +
                "         <postalBoxNo></postalBoxNo>\n" +
                "         <postalTown></postalTown>\n" +
                "         <postalCountry></postalCountry>\n" +
                "         <telephoneWork></telephoneWork>\n" +
                "         <telephoneHome></telephoneHome>\n" +
                "         <telephoneMobile></telephoneMobile>\n" +
                "         <physicalAddress></physicalAddress>\n" +
                "         <physicalTown></physicalTown>\n" +
                "         <physicalCountry></physicalCountry>\n" +
                "         <reportSector>2</reportSector>\n" +
                "         <reportReason>1</reportReason>\n" +
                "      </ws:getProduct123>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";

        return xml;
    }


    public static CrbResponse mapResponseXmlToJson(CrbResponse crbResponse, String xmlString) {
        CrbResponseData data = new CrbResponseData();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new java.io.StringReader(xmlString));
            Document document = builder.parse(inputSource);



            // Retrieve the values
            NodeList fullNameList = document.getElementsByTagName("fullName");
            NodeList otherNamesList = document.getElementsByTagName("otherNames");
            NodeList salutationList = document.getElementsByTagName("salutation");
            NodeList nationalIdList = document.getElementsByTagName("nationalID");
            NodeList passportNoList = document.getElementsByTagName("passportNo");
            NodeList serviceIdList = document.getElementsByTagName("serviceID");
            NodeList designationList = document.getElementsByTagName("designation");
            NodeList phoneNoList = document.getElementsByTagName("phoneNo");
            NodeList crbNameList = document.getElementsByTagName("crbName");




            // Check if elements exist and print them
            if (fullNameList.getLength() > 0) {
                data.setFullName(fullNameList.item(0).getTextContent());
                System.out.println("Full Name: " + fullNameList.item(0).getTextContent());
            }
            if (otherNamesList.getLength() > 0) {
                data.setOtherName(otherNamesList.item(0).getTextContent());
                System.out.println("Other Names: " + otherNamesList.item(0).getTextContent());
            }
            if (salutationList.getLength() > 0) {
                data.setSalutation(salutationList.item(0).getTextContent());
                System.out.println("Salutation: " + salutationList.item(0).getTextContent());
            }
            if (phoneNoList.getLength() > 0) {
                data.setPhoneNo(phoneNoList.item(0).getTextContent());
                System.out.println("phoneNo: " + phoneNoList.item(0).getTextContent());
            }

        if (nationalIdList.getLength() > 0) {
            data.setNationalID(nationalIdList.item(0).getTextContent());
            System.out.println("nationalId: " + nationalIdList.item(0).getTextContent());
        }
        if (passportNoList.getLength() > 0) {
            data.setPassportNo(passportNoList.item(0).getTextContent());
            System.out.println("passportNo: " + passportNoList.item(0).getTextContent());
        }
        if (serviceIdList.getLength() > 0) {
                data.setServiceID(serviceIdList.item(0).getTextContent());
                System.out.println("serviceId: " + serviceIdList.item(0).getTextContent());
            }
        if (designationList.getLength() > 0) {
            data.setDesignation(designationList.item(0).getTextContent());
            System.out.println("designation: " + designationList.item(0).getTextContent());
        }

            if (crbNameList.getLength() > 0) {
                data.setCrbName(crbNameList.item(0).getTextContent());
                System.out.println("crbName: " + crbNameList.item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        crbResponse.setData(data);
        return  crbResponse;
    }

}
