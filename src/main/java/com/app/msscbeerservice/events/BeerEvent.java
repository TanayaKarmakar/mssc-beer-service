package com.app.msscbeerservice.events;

import com.app.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    static final long serialVersionUID = -276653853843L;

    private final BeerDto beerDto;
}
