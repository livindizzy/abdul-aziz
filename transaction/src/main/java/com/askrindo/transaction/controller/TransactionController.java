package com.askrindo.transaction.controller;

import com.askrindo.transaction.model.request.MikroRumahkuRequest;
import com.askrindo.transaction.model.response.Response;
import com.askrindo.transaction.model.response.ValidationResponse;
import com.askrindo.transaction.service.MikroRumahKuTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Tag(name = "TRANSACTION API")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final MikroRumahKuTransactionService mikroRumahKuTransactionService;

    @Operation(summary = "Transaction API", description = "API to add data for Mikro rumahku")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success to add Data for Mikro rumahku"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @PostMapping("/v1/mikro-rumahku")
    public Response<ValidationResponse> transactionMikroRumahku(@RequestBody MikroRumahkuRequest request, @RequestParam (name = "kodeProduct") String kodeProduct) {
        return Response.success(mikroRumahKuTransactionService.execute(request, kodeProduct),"Success");
    }
}
