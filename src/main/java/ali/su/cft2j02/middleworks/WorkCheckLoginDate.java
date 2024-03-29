package ali.su.cft2j02.middleworks;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.annotation.LogTransformation;
import ali.su.cft2j02.config.LogConfig;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Order(3)
public class WorkCheckLoginDate implements MiddleWorker<List<Data>> {

    private final String logFilesDestination;

    public WorkCheckLoginDate(LogConfig logConfig) {
        this.logFilesDestination = logConfig.getLocation();
    }

    @Override
    @LogTransformation(logFileName = "WorkCheckLoginDate.log")
    public List<Data> doWork(List<Data> dataList) {
        var goodList = new ArrayList<Data>();
        var badList = new ArrayList<Data>();

        dataList.forEach(d -> {
            if (d.getLoginDate() == null) {
                badList.add(d);
            } else {
                goodList.add(d);
            }
        });

        if (!badList.isEmpty()) {
            saveLog(badList);
        }

        return goodList;
    }

    private void saveLog(List<Data> dataList) {
        var formatter = new SimpleDateFormat("yyyy_MM_dd'.log'");
        var fileName = logFilesDestination.concat(formatter.format(new Date()));

        try {
            var path = Path.of(logFilesDestination);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            for (var data : dataList) {
                String logRec = String.format("%s: %s\r\n", data.getFileName(), data);
                Files.write(Path.of(fileName), logRec.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в лог: " + e);
        }
    }
}
