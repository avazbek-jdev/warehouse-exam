package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndActiveTrueAndEnabledTrue(String phoneNumber);

    boolean existsByPhoneNumberAndIdAndEnabledTrue(String phoneNumber, Integer id);
    boolean existsByPhoneNumber(String phoneNumber);
}
