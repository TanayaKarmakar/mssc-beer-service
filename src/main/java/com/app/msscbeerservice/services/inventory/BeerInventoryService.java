package com.app.msscbeerservice.services.inventory;

import java.util.UUID;

/**
 * @author t0k02w6 on 15/05/21
 * @project mssc-beer-service
 */
public interface BeerInventoryService {
    Integer getOnhandInventory(UUID beerId);
}
