package springboot.brewery.mappers;

import org.mapstruct.Mapper;

import springboot.brewery.entities.Beer;
import springboot.brewery.model.BeerDTO;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);

}
