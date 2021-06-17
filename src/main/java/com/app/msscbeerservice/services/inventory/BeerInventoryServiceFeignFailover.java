package com.app.msscbeerservice.services.inventory;

import com.app.common.models.inventory.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author t0k02w6 on 17/06/21
 * @project mssc-beer-service
 */
@Component
@RequiredArgsConstructor
public class BeerInventoryServiceFeignFailover implements  InventoryServiceFeignClient{
    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
        return inventoryFailoverFeignClient.getOnhandInventory();
    }
}
