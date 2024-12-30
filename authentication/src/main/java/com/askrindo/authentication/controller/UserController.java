package com.askrindo.authentication.controller;

import com.askrindo.authentication.model.request.UserLoginRequest;
import com.askrindo.authentication.model.response.Response;
import com.askrindo.authentication.model.response.UserLoginResponse;
import com.askrindo.authentication.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "AUTHENTICATION API")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final LoginService loginService;


    @Operation(summary = "Sign-In User API", description = "API to authenticate user login and get JWT for authorization.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Welcome to system message"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @PostMapping("/v1/sign-in")
    public Response<UserLoginResponse> signIn(@RequestBody UserLoginRequest request) {
        UserLoginResponse userLoginResponse = loginService.execute(request);
        return Response.success(userLoginResponse,"Success");
    }
}
