package com.app.msscbeerservice.web.controller;

import com.app.msscbeerservice.services.BeerService;
import com.app.msscbeerservice.web.model.BeerDto;
import com.app.msscbeerservice.web.model.BeerPagedList;
import com.app.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    @GetMapping
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle) {
        if(pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if(pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        BeerPagedList list = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

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
