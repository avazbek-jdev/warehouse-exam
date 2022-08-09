package ai.ecma.warehouseexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.Notification;
import java.sql.Timestamp;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

//    Notification getBySendingNotificationDayGreaterThanEquals(Timestamp sendingNotificationDay);
      Optional<Notification> findBySendingNotificationDayLessThan(Timestamp sendingNotificationDay);
}
