package com.askrindo.authentication.controller;

import com.askrindo.authentication.model.response.GeneralListResponse;
import com.askrindo.authentication.model.response.Response;
import com.askrindo.authentication.service.GetListLookUpKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parameter")
@Tag(name = "PARAMETER API")
@RequiredArgsConstructor
@Slf4j
public class ParameterController {

    private final GetListLookUpKeyService getListLookUpKeyService;

    @GetMapping(value = { "/v1/get-look-up-key"})
    @Operation(summary = "Get Look Up Key")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully get Get Look Up Key"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to get Parameter Get Look Up Key"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
    public ResponseEntity<List<String>> getLookUpKey(@RequestParam(name = "lookUpGroup") String request) {
        List<String> listLookUpKey = getListLookUpKeyService.execute(request);
        return ResponseEntity.ok(listLookUpKey);
    }


}
