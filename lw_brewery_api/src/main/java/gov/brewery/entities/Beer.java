package gov.brewery.entities;

import gov.brewery.model.converter.BeerStyleConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.deloitte.nextgen.framework.automate.annotations.Endpoint;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;

import gov.brewery.constants.BreweryConstants;
import gov.brewery.mappers.BeerMapper;
import gov.brewery.model.BeerDTO;
import gov.brewery.model.BeerStyle;
import gov.brewery.repositories.BeerRepo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
//@Getter
//@Setter
//@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityType(type= TypeEnum.ONE)
@Data
@Endpoint(
		name = BreweryConstants.EndpointNames.Beer,
        request = BeerDTO.class,
        response = BeerDTO.class,
        mapper = BeerMapper.class,
        excludeResource = false,
        excludeService = false,
        repository = BeerRepo.class
)
@Table(name="beer")
public class Beer extends TypeOneBaseEntity<UUID>{
	
	//check sequence generator ng-persistence generator package 

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

//    @Version
//    private Integer version;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String beerName;

    @NotNull
    @Convert(converter = BeerStyleConverter.class)
    private BeerStyle beerStyle;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String upc;
    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;

    @OneToMany(mappedBy = "beer")
    private Set<BeerOrderLine> beerOrderLines;

//    @Builder.Default
    @ManyToMany
    @JoinTable(name = "beer_category",
            joinColumns = @JoinColumn(name = "beer_id"),
       inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){
        this.categories.add(category);
        category.getBeers().add(this);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getBeers().remove(category);
    }

//    @CreationTimestamp
//    private LocalDateTime createdDate;
//
//    @UpdateTimestamp
//    private LocalDateTime updateDate;
}
