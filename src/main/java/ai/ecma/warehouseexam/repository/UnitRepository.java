package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    default boolean existsByNameAndIdAndActiveTrue(String name, Integer id) {
        return false;
    }

    boolean existsByNameAndIdNot(String name);
}
