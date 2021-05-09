package com.app.msscbeerservice.bootstrap;

import com.app.msscbeerservice.domain.Beer;
import com.app.msscbeerservice.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author t0k02w6 on 08/05/21
 * @project mssc-beer-service
 */
@Component
public class BeerLoader implements CommandLineRunner {
    @Autowired
    private BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango BOB")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(39339398383L)
                    .price(new BigDecimal("12.95"))
                    .build());


            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(23633434436L)
                    .price(new BigDecimal("12.95"))
                    .build());
        }

        System.out.println("Loaded Beers : " + beerRepository.count());
    }
}
