package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.IncomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface IncomeProductRepository extends JpaRepository<IncomeProduct,Integer> {

      List<IncomeProduct> getByExpiredDateLessThanEqual(Timestamp expiredDate);



}
