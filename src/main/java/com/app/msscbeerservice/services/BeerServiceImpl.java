package com.app.msscbeerservice.services;

import com.app.common.exception.NotFoundException;
import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.repositories.BeerRepository;
import com.app.msscbeerservice.web.mappers.BeerMapper;
import com.app.common.models.BeerDto;
import com.app.common.models.BeerPagedList;
import com.app.common.models.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author t0k02w6 on 10/05/21
 * @project mssc-beer-service
 */
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand) {
        if(showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Item Not found")));
        } else {
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Item Not found")));
        }
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

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        log.info("Fetching beer has started");
        BeerPagedList beerList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if(showInventoryOnHand) {
            beerList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else {
            beerList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }


        return beerList;
    }

    @Cacheable(cacheNames = "beerUpcCache", key = "#upc")
    @Override
    public BeerDto getBeerByUpc(String upc) {
        log.info("Started fetching beer by upc {}", upc);
        Beer beer = beerRepository.findByUpc(upc);
        return beerMapper.beerToBeerDto(beer);
    }
}
