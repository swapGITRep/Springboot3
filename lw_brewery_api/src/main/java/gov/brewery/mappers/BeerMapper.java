package gov.brewery.mappers;

import org.mapstruct.Mapper;

import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import com.deloitte.nextgen.framework.commons.mapper.TypeZeroRequestResponseMapper;

import gov.brewery.entities.Beer;
import gov.brewery.model.BeerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerMapper  extends RequestResponseMapper<BeerDTO, BeerDTO, Beer>{

//    Beer beerDtoToBeer(BeerDTO dto);
//
//    BeerDTO beerToBeerDto(Beer beer);

}
