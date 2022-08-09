package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByNameAndActiveTrue(String name);

    boolean existsByNameAndIdNotAndActiveTrue(String name, Integer id);
}
