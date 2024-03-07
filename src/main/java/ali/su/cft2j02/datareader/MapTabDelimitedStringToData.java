package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.config.ReaderConfig;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MapTabDelimitedStringToData implements MapperSourceToData<String, Data> {
    private final SimpleDateFormat sdf;

    public MapTabDelimitedStringToData(ReaderConfig readerConfig) {
        this.sdf = new SimpleDateFormat(readerConfig.getDateFormat());
    }

    @Override
    public Data apply(String s, String fileName) {
        final var cols = s.split("\t");
        Date loginDate = null;
        try {
            loginDate = sdf.parse(cols[4]);
        } catch (ParseException e) {
            //throw new RuntimeException(e);
        }
        return new Data(cols[0], cols[1], cols[2], cols[3], loginDate, cols[5], fileName);
    }
}
