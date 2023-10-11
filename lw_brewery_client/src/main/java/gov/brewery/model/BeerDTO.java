package gov.brewery.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt, Spring Framework Guru.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerDTO {
    private UUID id;
    private Integer version;

    @NotBlank
    @NotNull
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @NotNull
    @NotBlank
    private String upc;
    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;
    
//    private LocalDateTime createdDate;
//    private LocalDateTime updateDate;


    @Override
    public String toString() {
        return "BeerDTO{" +
                "id=" + id +
                ", version=" + version +
                ", beerName='" + beerName + '\'' +
                ", beerStyle=" + beerStyle +
                ", upc='" + upc + '\'' +
                ", quantityOnHand=" + quantityOnHand +
                ", price=" + price +
                '}';
    }
}
