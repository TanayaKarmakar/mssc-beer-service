package com.app.common.models.events;

import com.app.common.models.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
