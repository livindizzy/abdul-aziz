package com.askrindo.transaction.controller;

import com.askrindo.transaction.model.request.MikroRumahkuRequest;
import com.askrindo.transaction.model.response.Response;
import com.askrindo.transaction.model.response.ValidationResponse;
import com.askrindo.transaction.service.MikroRumahKuTransactionService;
import com.askrindo.transaction.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/transaction")
@Tag(name = "TRANSACTION API")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final MikroRumahKuTransactionService mikroRumahKuTransactionService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Transaction API", description = "API to add data for Mikro rumahku")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success to add Data for Mikro rumahku"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong")})
    @PostMapping("/v1/mikro-rumahku")
    public Response<ValidationResponse> transactionMikroRumahku(@RequestHeader("Authorization") String token,
                                                                @RequestBody MikroRumahkuRequest request,
                                                                @RequestParam(name = "kodeProduct") String kodeProduct) {
        jwtUtil.extractJwt(token, request);
        ValidationResponse response = mikroRumahKuTransactionService.execute(request, kodeProduct);
        return Response.success(response, "Success");
    }


}
