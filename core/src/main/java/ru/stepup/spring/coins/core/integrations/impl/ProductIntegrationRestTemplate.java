package ru.stepup.spring.coins.core.integrations.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.ProductIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.CoinsExecuteDtoRq;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDto;

import java.util.Collections;
import java.util.List;

public class ProductIntegrationRestTemplate implements ProductIntegration {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductIntegrationRestTemplate.class.getName());

    public ProductIntegrationRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> products(Long userId) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<CoinsExecuteDtoRq> request = new HttpEntity<>(
                    httpHeaders
            );

            ResponseEntity<List<ProductDto>> response = restTemplate.exchange("/products/filter?userId=" + userId
                    , HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>(){});
            logger.info("response: {}", response);
            return response.getBody();
        } catch (IntegrationException e) {
            logger.info("error body: {}", e.getIntegrationErrorDto());
            return null;
        }
    }

    @Override
    public ProductDto product(Long id) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<CoinsExecuteDtoRq> request = new HttpEntity<>(
                    httpHeaders
            );

            ResponseEntity<ProductDto> response = restTemplate.exchange("/products/" + id
                    , HttpMethod.GET, null, new ParameterizedTypeReference<ProductDto>(){});
            logger.info("response: {}", response);
            if(response.getStatusCode().isError()) {
                throw new BadRequestException("Ошибка", "PRODUCT_ERROR");
            }

            return response.getBody();
        } catch (IntegrationException e) {
            logger.info("error body: {}", e.getIntegrationErrorDto());
            return null;
        }
    }
}
