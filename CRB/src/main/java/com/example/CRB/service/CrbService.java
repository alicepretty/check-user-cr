package com.example.CRB.service;

import com.example.CRB.dto.Crbdto;
import com.example.CRB.model.CrbRequest;
import com.example.CRB.model.CrbResponse;
import com.example.CRB.model.CrbResponseData;
import com.example.CRB.service.map.CrbMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrbService {

    @Autowired
    HttpHandler httpHandler;

    private String escapeXml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    public CrbResponse checkUserInCrb(CrbRequest request) {
        CrbResponse response = new CrbResponse();

        if (!isValidNationalID(request.getNationalID())) {
            response.setStatus(400);
            response.setMessage("Invalid national ID format");
            return response;
        }

        try {
            log.info("Checking user in CRB: {}", request.getName1());
            String requestBody = CrbMap.mapCreateSoapEnvelope(request);
            System.err.println(requestBody);

            // Post to TransUnion
            String resultXml = httpHandler.postApi(requestBody);
            response = CrbMap.mapResponseXmlToJson(response, resultXml);

            System.err.println("\nRESULT CRB\n" + resultXml);

            response.setStatus(200);
            response.setMessage("Successsful");


        } catch (Exception e) {
            log.error("Error checking user in CRB", e);

            response.setStatus(500);
            response.setMessage("Failed, Exc: " + e.getMessage());
        }

        return response;
    }
    private boolean isValidNationalID(String nationalID) {
        if (nationalID == null || nationalID.isEmpty()) {
            return false;
        }
        return nationalID.matches("^\\d{16}$");
    }

    public static void main(String[] args) {
        try {
            // Assuming your XML string is stored in a variable called 'xmlString'
            String xmlString = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><S:Body><ns2:getProduct123Response xmlns:ns2=\"http://ws.rw.crbws.transunion.ke.co/\"><return><alsoKnownAsList><fullName>PALUKU MULWAHALI SERGE</fullName><otherNames>MULWAHALI SERGE</otherNames><salutation>MR.</salutation><surname>PALUKU</surname></alsoKnownAsList></return></ns2:getProduct123Response></S:Body></S:Envelope>";

            // Parse the XML string
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new java.io.StringReader(xmlString));
            Document document = builder.parse(inputSource);

            // Retrieve the values
            NodeList fullNameList = document.getElementsByTagName("fullName");
            NodeList otherNamesList = document.getElementsByTagName("otherNames");
            NodeList salutationList = document.getElementsByTagName("salutation");
            NodeList surnameList = document.getElementsByTagName("surname");

            // Check if elements exist and print them
            if (fullNameList.getLength() > 0) {
                System.out.println("Full Name: " + fullNameList.item(0).getTextContent());
            }
            if (otherNamesList.getLength() > 0) {
                System.out.println("Other Names: " + otherNamesList.item(0).getTextContent());
            }
            if (salutationList.getLength() > 0) {
                System.out.println("Salutation: " + salutationList.item(0).getTextContent());
            }
            if (surnameList.getLength() > 0) {
                System.out.println("Surname: " + surnameList.item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}