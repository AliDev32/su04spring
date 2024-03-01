package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class MapStringTabDelimitedToData implements MapperSourceToData<String, Data> {
    private SimpleDateFormat sdf;

    public MapStringTabDelimitedToData(@Qualifier("dateFormat") String dateFormat) {
        this.sdf = new SimpleDateFormat(dateFormat);
    }

    @Override
    public Data apply(String s) {
        var cols = s.split("\t");
        try {
            return new Data(cols[0], cols[1], cols[2], cols[3], sdf.parse(cols[4]), cols[5]);
        } catch (ParseException e) {
            //TODO:
            throw new RuntimeException(e);
        }
    }
}
