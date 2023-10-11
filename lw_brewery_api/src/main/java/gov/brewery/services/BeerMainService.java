package gov.brewery.services;

import com.deloitte.nextgen.framework.commons.payload.response.AuthResponse;
import com.nimbusds.jose.JOSEException;
import gov.brewery.entities.Beer;
import gov.brewery.model.CustomerResponseDTO;
import org.springframework.data.domain.Page;

import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.text.ParseException;

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

    AuthResponse getToken(String token) throws JOSEException, ParseException, IOException;

    String getEmployee(String empId);

    String updateEmployee(String empId);

    void deleteEmployee(String empId);

    String getEmployeefromBean(String key);

    String checkForLog(String abc);

    String getTime(String value);
}
