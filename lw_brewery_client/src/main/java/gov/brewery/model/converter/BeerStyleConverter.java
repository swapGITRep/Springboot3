package gov.brewery.model.converter;

import com.fasterxml.jackson.annotation.JsonCreator;
import gov.brewery.model.BeerStyle;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Converter
public class BeerStyleConverter implements AttributeConverter<BeerStyle, String> {

    @Override
    public String convertToDatabaseColumn(BeerStyle type) {
        if (type == null) {
            return null;
        }
        return type.getName();
    }

    @Override
    @JsonCreator
    public BeerStyle convertToEntityAttribute(final String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(BeerStyle.values())
                .filter(style -> style.getName().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}