package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.IncomeWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeWarehouseRepository extends JpaRepository<IncomeWarehouse,Integer> {

boolean getByIdAndFactureNumber(Integer factureNumber);

}
