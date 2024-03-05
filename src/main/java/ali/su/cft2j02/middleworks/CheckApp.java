package ali.su.cft2j02.middleworks;

import ali.su.cft2j02.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class CheckApp implements MiddleWorker<List<Data>> {
    @Override
    public List<Data> doWork(List<Data> dataList) {
        dataList.forEach(d -> {
            if ("web".equals(d.getAppType()) || "mobile".equals(d.getAppType())) {
                return;
            } else {
                d.setAppType("other:" + d.getAppType());
            }
        });
        return dataList;
    }
}
