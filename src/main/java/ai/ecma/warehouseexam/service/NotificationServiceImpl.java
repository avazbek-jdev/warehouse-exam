package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.repository.NotificationRepository;
import ai.ecma.warehouseexam.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification getBySendingNotificationDay(Timestamp timestamp) {
        return notificationRepository.findBySendingNotificationDayLessThan(timestamp).orElse(null);
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
}
