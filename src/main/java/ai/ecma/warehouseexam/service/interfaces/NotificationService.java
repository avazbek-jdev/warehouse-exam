package ai.ecma.warehouseexam.service.interfaces;

import javax.management.Notification;
import java.sql.Timestamp;

public interface NotificationService {

    Notification getBySendingNotificationDay(Timestamp timestamp);

    void save(Notification notification);
    

}
