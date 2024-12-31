package com.askrindo.transaction.adaptor.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "authentication", url = "${spring.feign.client.config.lookup-service.url}")
public interface ParameterAdaptor {

    @GetMapping(value = "/v1/get-look-up-key")
    ResponseEntity<List<String>> getLookUpKey(@RequestParam String lookUpGroup);
}
