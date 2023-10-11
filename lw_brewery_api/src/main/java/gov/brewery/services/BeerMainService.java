package gov.brewery.services;

import gov.brewery.entities.Beer;
import gov.brewery.model.CustomerResponseDTO;
import org.springframework.data.domain.Page;

import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerMainService {

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);

    List<BeerDTO> findAllBeersByQuantityOnHand(Integer quantityOnHand);

}
