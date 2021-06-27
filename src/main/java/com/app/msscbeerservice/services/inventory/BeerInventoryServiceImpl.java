package com.app.msscbeerservice.services.inventory;

import com.app.common.models.inventory.BeerInventoryDto;
import com.app.common.util.URIConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author t0k02w6 on 15/05/21
 * @project mssc-beer-service
 */
@Slf4j
@Profile("!local-discovery")
@Service
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = true)
public class BeerInventoryServiceImpl implements BeerInventoryService{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sfg.brewery.beer-inventory-service-host}")
    private String beerInventoryServiceHost;

    @Value("${sfg.brewery.inventory-user}")
    private String inventoryUser;

    @Value("${sfg.brewery.inventory-password}")
    private String inventoryPassword;

    public BeerInventoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(inventoryUser, inventoryPassword)
                .build();
    }

    @Override
    public Integer getOnhandInventory(UUID beerId) {
        log.debug("Calling inventory service");
        ParameterizedTypeReference<List<BeerInventoryDto>> typeRef = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(beerInventoryServiceHost + URIConstants.INVENTORY_PATH,
                HttpMethod.GET,
                null,
                typeRef, beerId);
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
