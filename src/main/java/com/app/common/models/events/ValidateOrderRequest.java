package com.app.common.models.events;

import com.app.common.models.order.BeerOrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author t0k02w6 on 05/06/21
 * @project mssc-beer-service
 */
@Data
@NoArgsConstructor
public class ValidateOrderRequest {
    private BeerOrderDto beerOrderDto;
}
