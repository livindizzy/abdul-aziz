package com.askrindo.authentication.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginRequest implements Serializable {

    @Schema(example = "myusername", name="Your registered username in user management")
    private String username;
    @Schema(example = "xxxxyyyy", name="Your secret password")
    private String password;

}
