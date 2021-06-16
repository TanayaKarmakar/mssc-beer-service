package com.app.msscbeerservice.services.inventory;

import com.app.common.models.inventory.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author t0k02w6 on 16/06/21
 * @project mssc-beer-service
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Profile("local-discovery")
public class BeerInventoryServiceFeign implements BeerInventoryService {
    private final InventoryServiceFeignClient inventoryServiceFeignClient;


    @Override
    public Integer getOnhandInventory(UUID beerId) {
        log.info("Calling inventory service - beerId: {}", beerId);
        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnhandInventory(beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.info("BeerId: {} on Hand is: {}", beerId, onHand);
        return onHand;
    }
}
