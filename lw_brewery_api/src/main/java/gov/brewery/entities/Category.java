package gov.brewery.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;

import gov.brewery.model.BeerStyle;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EntityType(type= TypeEnum.ZERO)
@Table(name="category")
public class Category extends TypeZeroBaseEntity<UUID>{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

//    @Version
//    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String description;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "beer_category",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "beer_id"))
    private Set<Beer> beers = new HashSet<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        return getDescription() != null ? getDescription().equals(category.getDescription()) : category.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
