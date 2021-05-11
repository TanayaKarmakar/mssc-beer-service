package com.app.msscbeerservice.web.controller;

import com.app.msscbeerservice.services.BeerService;
import com.app.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author t0k02w6 on 07/05/21
 * @project mssc-beer-service
 */

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @Autowired
    private BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        //return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
        //return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.CREATED);
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BeerDto> updateBeer(@Validated @RequestBody BeerDto beerDto) {
        //return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
        return new ResponseEntity<>(beerService.updateBeer(beerDto), HttpStatus.OK);
    }
}
