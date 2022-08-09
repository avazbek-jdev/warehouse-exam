package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot (String name, Integer id);
}
