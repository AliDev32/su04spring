package ali.su.cft2j02.middleworks;

import ali.su.cft2j02.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiddleWorksRunner {

    private List<MiddleWorker<List<Data>>> workers;

    public MiddleWorksRunner(List<MiddleWorker<List<Data>>> workers) {
        this.workers = workers;
    }

    public List<Data> run(List<Data> dataList) {
        for (var w : workers) {
            dataList = w.doWork(dataList);
        }
        return dataList;
    }

    public List<MiddleWorker<List<Data>>> getWorkers() {
        return workers;
    }
}
