package ali.su.cft2j02.middleworks;

import ali.su.cft2j02.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiddleWorksRunner implements MiddleWorker<List<Data>> {

    private final List<MiddleWorker<List<Data>>> workers;

    public MiddleWorksRunner(List<MiddleWorker<List<Data>>> workers) {
        this.workers = workers;
    }

    @Override
    public List<Data> doWork(List<Data> dataList) {
        for (var w : workers) {
            dataList = w.doWork(dataList);
        }
        return dataList;
    }
}
