package springboot.brewery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.brewery.entities.Category;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
