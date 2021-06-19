package com.app.msscbeerservice.services.inventory;

import com.app.common.models.inventory.BeerInventoryDto;
import com.app.common.util.URIConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

/**
 * @author t0k02w6 on 16/06/21
 * @project mssc-beer-service
 */
@FeignClient(name = "beer-inventory-service", fallback = BeerInventoryServiceFeignFailover.class)
public interface InventoryServiceFeignClient {

    @GetMapping(URIConstants.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);
}
