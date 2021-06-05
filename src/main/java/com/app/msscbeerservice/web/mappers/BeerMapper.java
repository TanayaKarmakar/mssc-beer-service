package com.app.msscbeerservice.web.mappers;

import com.app.msscbeerservice.domain.Beer;
import com.app.common.models.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

/**
 * @author t0k02w6 on 09/05/21
 * @project mssc-beer-service
 */
@Mapper(uses = {DateMapper.class}, imports = UUID.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    @Mapping(source = "id", target = "id", defaultExpression = "java(UUID.fromString(beer.getId().toString()))")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(source = "id", target = "id", defaultExpression = "java(UUID.fromString(beerDto.getId().toString()))")
    Beer beerDtoToBeer(BeerDto beerDto);

    @Mapping(source = "id", target = "id", defaultExpression = "java(UUID.fromString(beer.getId().toString()))")
    BeerDto beerToBeerDtoWithInventory(Beer beer);
}
