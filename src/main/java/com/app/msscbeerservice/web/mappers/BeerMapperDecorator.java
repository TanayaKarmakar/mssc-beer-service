package com.app.msscbeerservice.web.mappers;


import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.services.inventory.BeerInventoryService;
import com.app.common.models.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author t0k02w6 on 15/05/21
 * @project mssc-beer-service
 */
public abstract class BeerMapperDecorator implements BeerMapper{
    @Autowired
    private BeerInventoryService beerInventoryService;

    @Autowired
    private BeerMapper beerMapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }
}
