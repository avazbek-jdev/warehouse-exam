package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.OutcomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutcomeProductRepository extends JpaRepository<OutcomeProduct,Integer> {

    @Query(value = "select max(measurement_amount), measurement_id from outcome_product", nativeQuery = true)
    OutcomeProduct getMaxMeasurementAmount();

}
