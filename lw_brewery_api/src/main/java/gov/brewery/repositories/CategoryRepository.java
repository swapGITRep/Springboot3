package gov.brewery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.brewery.entities.Category;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
//@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
