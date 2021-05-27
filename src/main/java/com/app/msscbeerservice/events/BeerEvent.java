package com.app.msscbeerservice.events;

import com.app.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {
    static final long serialVersionUID = -276653853843L;

    private BeerDto beerDto;
}
