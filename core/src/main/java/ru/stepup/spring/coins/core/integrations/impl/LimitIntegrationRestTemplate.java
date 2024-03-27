package ru.stepup.spring.coins.core.integrations.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.LimitIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LimitIntegrationRestTemplate implements LimitIntegration {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LimitIntegrationRestTemplate.class.getName());

    public LimitIntegrationRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ChangeRemainDto> changeRemain(Long userId, BigDecimal sum) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("userId", userId.toString());
            uriVariables.put("sum", sum.toString());

            ResponseEntity<ChangeRemainDto> response = restTemplate.exchange("/change-remain?userId={userId}&sum={sum}"
                    , HttpMethod.GET, null, new ParameterizedTypeReference<ChangeRemainDto>() {}
                    , uriVariables);
            logger.info("response: {}", response);
            return response;
        } catch (IntegrationException e) {
            logger.info("error body: {}", e.getIntegrationErrorDto());
            return null;
        }
    }
}
