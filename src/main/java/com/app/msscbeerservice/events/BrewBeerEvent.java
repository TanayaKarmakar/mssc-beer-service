package com.app.msscbeerservice.events;

import com.app.msscbeerservice.web.model.BeerDto;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
