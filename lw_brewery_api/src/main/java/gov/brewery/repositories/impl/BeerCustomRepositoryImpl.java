package gov.brewery.repositories.impl;

import gov.brewery.entities.Beer;
import gov.brewery.entities.QBeer;
import gov.brewery.generated.repository.BeerRepository;
import gov.brewery.mappers.BeerMapper;
import gov.brewery.model.BeerDTO;
import gov.brewery.repositories.IBeerCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements IBeerCustomRepository {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Iterable<Beer> findAllBeersByQuantityOnHand(Integer quantityOnHand) {
        QBeer qBeer = QBeer.beer;
        return beerRepository.findAll(qBeer.quantityOnHand.gt(quantityOnHand));
    }
}
