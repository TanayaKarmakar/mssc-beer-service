package com.app.msscbeerservice.repositories;

import com.app.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author t0k02w6 on 08/05/21
 * @project mssc-beer-service
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
