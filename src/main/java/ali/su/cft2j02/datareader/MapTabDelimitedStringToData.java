package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.config.LogConfig;
import ali.su.cft2j02.config.ReaderConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MapTabDelimitedStringToData implements MapperSourceToData<String, Data> {
    private final SimpleDateFormat sdf;
    private final String logFilesDestination;

    public MapTabDelimitedStringToData(ReaderConfig readerConfig, LogConfig logConfig) {
        this.sdf = new SimpleDateFormat(readerConfig.getDateFormat());
        this.logFilesDestination = logConfig.getLocation();
    }

    @Override
    public Data apply(String s, String fileName) {
        final var cols = s.split("\t");
        if (cols.length == 6) {
            Date loginDate = null;
            try {
                loginDate = sdf.parse(cols[4]);
            } catch (ParseException e) {
                //throw new RuntimeException(e);
            }
            return new Data(cols[0], cols[1], cols[2], cols[3], loginDate, cols[5], fileName);
        } else {
            saveLog(s);
            return null;
        }
    }

    private void saveLog(String str) {
        var formatter = new SimpleDateFormat("'badrows_'yyyy_MM_dd'.log'");
        var fileName = logFilesDestination.concat(formatter.format(new Date()));

        try {
            var path = Path.of(logFilesDestination);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.writeString(Path.of(fileName), str, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ошибка записи в лог: " + e);
        }
    }
}
