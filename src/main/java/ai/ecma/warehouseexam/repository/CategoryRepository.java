package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndId(String name, Integer id);


}
