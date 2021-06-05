package com.app.msscbeerservice.services;

import com.app.common.models.BeerDto;
import com.app.common.models.BeerPagedList;
import com.app.common.models.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @author t0k02w6 on 10/05/21
 * @project mssc-beer-service
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

    BeerDto getBeerByUpc(String upc);
}
