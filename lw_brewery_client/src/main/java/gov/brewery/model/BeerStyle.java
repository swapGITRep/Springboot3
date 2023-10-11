package gov.brewery.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by jt, Spring Framework Guru.
 */
public enum BeerStyle {
    LAGER, PILSNER, STOUT, GOSE, PORTER, ALE, WHEAT, IPA, PALE_ALE, SAISON;

    @JsonValue
    public String getName() {
        return this.name();
    }
}
