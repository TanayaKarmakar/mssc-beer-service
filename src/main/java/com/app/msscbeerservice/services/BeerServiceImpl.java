package com.app.msscbeerservice.services;

import com.app.msscbeerservice.common.exception.NotFoundException;
import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.repositories.BeerRepository;
import com.app.msscbeerservice.web.mappers.BeerMapper;
import com.app.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author t0k02w6 on 10/05/21
 * @project mssc-beer-service
 */
@Service
public class BeerServiceImpl implements BeerService {
    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Item Not found")));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto) {
        Beer beerToBeUpdated = beerRepository.findById(beerDto.getId()).orElseThrow(() -> new NotFoundException("Item Not Found"));
        Beer updatedBeer = beerMapper.beerDtoToBeer(beerDto);
        updatedBeer = beerRepository.save(updatedBeer);
        return beerMapper.beerToBeerDto(updatedBeer);
    }
}
