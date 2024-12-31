package com.askrindo.authentication.service;

import com.askrindo.authentication.repository.LookUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetListLookUpKeyService {
    private final LookUpRepository lookUpRepository;

    public List<String> execute(String input) {
        log.info("Start to get Look Up Key, with look up group {}", input);
        if (org.springframework.util.ObjectUtils.isEmpty(input)) {
            log.error("Look Up Key is empty");
            return Collections.emptyList();
        }
        List<String> listLookUpKey = lookUpRepository.getListLookUpKeyByLookupGroup(input);
        log.info("End to get Look Up Key");
        return listLookUpKey;
    }
}
