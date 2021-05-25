package com.app.msscbeerservice.services;

import com.app.msscbeerservice.config.JmsConfig;
import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.events.BrewBeerEvent;
import com.app.msscbeerservice.repositories.BeerRepository;
import com.app.msscbeerservice.services.inventory.BeerInventoryService;
import com.app.msscbeerservice.web.mappers.BeerMapper;
import com.app.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author t0k02w6 on 25/05/21
 * @project mssc-beer-service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BreweingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min onhand is : " + beer.getMinOnHand());
            log.debug("Inventory is : " + invQOH);

            if(beer.getMinOnHand() >= invQOH) {
                BeerDto beerDto = beerMapper.beerToBeerDto(beer);
                jmsTemplate.convertAndSend(JmsConfig.BREWEING_REQUEST_QUEUE, new BrewBeerEvent(beerDto));
            }
        });
    }
}
