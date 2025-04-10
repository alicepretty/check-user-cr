package com.example.CRB.controller;

import com.example.CRB.dto.Crbdto;
import com.example.CRB.model.CrbRequest;
import com.example.CRB.model.CrbResponse;
import com.example.CRB.service.CrbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for CRB check API
 */
@Slf4j
@RestController
@RequestMapping("/api/crb")
@RequiredArgsConstructor
@Tag(name = "CRB API", description = "API for checking if users are in Credit Reference Bureau")
public class CrbController {

    private final CrbService crbService;

    @PostMapping("/check")
    @Operation(
            summary = "Check if a user is in CRB",
            description = "Takes user details and checks if they are listed in the Credit Reference Bureau"
    )
    public ResponseEntity<CrbResponse> checkUserInCrb(@RequestBody CrbRequest request) {

        CrbResponse response = crbService.checkUserInCrb(request);
        HttpStatus httpStatus = response.getStatus() == 200 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(response);
    }
}