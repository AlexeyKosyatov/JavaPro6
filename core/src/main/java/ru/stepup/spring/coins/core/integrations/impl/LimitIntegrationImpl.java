package ru.stepup.spring.coins.core.integrations.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.LimitIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LimitIntegrationImpl implements LimitIntegration {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LimitIntegrationImpl.class.getName());

    public LimitIntegrationImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ChangeRemainDto changeRemain(Long userId, BigDecimal sum) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.set("userId", String.valueOf(userId));

            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("sum", sum.toString());

            ChangeRemainDto response = restTemplate.exchange("/limit/change-remain?&sum={sum}"
                    , HttpMethod.PUT, entity, new ParameterizedTypeReference<ChangeRemainDto>() {}
                    , uriVariables).getBody();
            logger.info("response: {}", response);
            return response;
        } catch (IntegrationException e) {
            logger.info("error body: {}", e.getIntegrationErrorDto());
            return null;
        }
    }
}
