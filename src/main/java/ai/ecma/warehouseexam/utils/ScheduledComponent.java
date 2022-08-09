package ai.ecma.warehouseexam.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledComponent {

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void deleteExpiredSales() {
//        List<Sale> saleList = saleRepository.findAllByExpiredDateLessThan(new Timestamp(System.currentTimeMillis()));
//        saleRepository.deleteAll(saleList);
    }

}
