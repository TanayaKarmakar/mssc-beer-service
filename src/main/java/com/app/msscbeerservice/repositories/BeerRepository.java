package com.app.msscbeerservice.repositories;

import com.app.msscbeerservice.domain.Beer;
import com.app.common.models.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author t0k02w6 on 08/05/21
 * @project mssc-beer-service
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Beer findByUpc(String upc);
}
