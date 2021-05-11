package com.app.msscbeerservice.services;

import com.app.msscbeerservice.web.model.BeerDto;
import java.util.UUID;

/**
 * @author t0k02w6 on 10/05/21
 * @project mssc-beer-service
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(BeerDto beerDto);
}
