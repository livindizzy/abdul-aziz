package com.askrindo.authentication.service;

import com.askrindo.authentication.model.entity.User;
import com.askrindo.authentication.model.projection.UserView;
import com.askrindo.authentication.model.request.UserLoginRequest;
import com.askrindo.authentication.model.response.UserLoginResponse;
import com.askrindo.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserLoginResponse execute(UserLoginRequest input) {
        log.info("Login request : {}", input.getUsername());
        Optional<User> userLogin = userRepository.findByUsername(input.getUsername());
        if (userLogin.isPresent()) {
            UserView user = userRepository.getUserDataByUsernameAndPassword(input.getUsername(), input.getPassword());
            if (ObjectUtils.isEmpty(user)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            }
            String token = jwtService.generateToken(user);
            return UserLoginResponse.builder()
                    .username(user.getUsername())
                    .roleName(user.getRoleName())
                    .token(token)
                    .build();
        }
        log.info("End Of Login");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
}
