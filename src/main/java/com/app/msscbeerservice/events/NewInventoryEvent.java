package com.app.msscbeerservice.events;

import com.app.msscbeerservice.web.model.BeerDto;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
