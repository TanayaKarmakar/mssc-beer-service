package com.app.msscbeerservice.web.mappers;

import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * @author t0k02w6 on 09/05/21
 * @project mssc-beer-service
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
