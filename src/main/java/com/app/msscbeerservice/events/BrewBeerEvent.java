package com.app.msscbeerservice.events;

import com.app.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
