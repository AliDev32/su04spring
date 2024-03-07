package ali.su.cft2j02.middleworks;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.annotation.LogTransformation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@Order(1)
public class WorkCheckFIO implements MiddleWorker<List<Data>> {
    @Override
    @LogTransformation(logFileName = "WorkCheckFIO.log")
    public List<Data> doWork(List<Data> dataList) {
        dataList.forEach(d -> {
            d.setLastName(StringUtils.capitalize(d.getLastName()));
            d.setFirstName(StringUtils.capitalize(d.getFirstName()));
            d.setPatronymic(StringUtils.capitalize(d.getPatronymic()));
        });
        return dataList;
    }
}
