package gov.brewery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.nextgen.framework.persistence.repository.TypeZeroRepository;

import gov.brewery.entities.Beer;
import gov.brewery.model.BeerStyle;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
//@Repository
public interface BeerRepo extends TypeZeroRepository<Beer, UUID> {

	Page<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);

	Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);

	Page<Beer> findAllByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);
}
