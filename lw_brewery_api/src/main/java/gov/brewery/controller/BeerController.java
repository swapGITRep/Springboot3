package gov.brewery.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParseException;

import gov.brewery.exception.BannedBeerException;
import gov.brewery.exception.BeerNotFoundException;
import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;
import gov.brewery.services.BeerMainService;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerMainService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> updateBeerPatchById(@PathVariable("beerId")UUID beerId, @RequestBody BeerDTO beer){

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_USER')")
    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("beerId") UUID beerId){

        if(Boolean.FALSE.equals( beerService.deleteById(beerId))){
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> updateById(@PathVariable("beerId")UUID beerId, @Validated @RequestBody BeerDTO beer){

        if( beerService.updateBeerById(beerId, beer).isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<HttpStatus> handlePost(@Validated @RequestBody BeerDTO beer){

        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = BEER_PATH)
    public Page<BeerDTO> listBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize){
        return beerService.listBeers(beerName, beerStyle, showInventory, pageNumber, pageSize);
    }


    @GetMapping(value = BEER_PATH_ID)
    public BeerDTO getBeerById(@RequestHeader (name="Authorization") @PathVariable("beerId") UUID beerId){

        log.debug("Get Beer by Id - in controller");

//        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
        
        return beerService.getBeerById(beerId).orElseThrow(() -> new BeerNotFoundException(404, BEER_PATH_ID, BEER_PATH));
    }
    
    @GetMapping(value = BEER_PATH+"/test")
    public Page<BeerDTO> testBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize) throws JsonParseException{
    	if("Error".equals(beerName)) {
    		log.debug("Beer Name Error in test api- in controller");
    		throw new ArrayIndexOutOfBoundsException();
    	}else if("Expired".equals(beerName)) {
    		log.debug("Beer Name Expired in test api- in controller");
    		throw new JsonParseException("Expired Beers not allowed");
    	}else if("Banned".equals(beerName)) {
    		log.debug("Beer Name Banned in test api- in controller");
    		throw new BannedBeerException(406, BEER_PATH_ID, BEER_PATH);
    	}
        return beerService.listBeers(beerName, beerStyle, showInventory, pageNumber, pageSize);
    }


}
