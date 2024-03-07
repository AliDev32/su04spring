package ali.su.cft2j02.datareader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date mapStringToDate(String str, String format) {
        if (str != null) {
            try {
                return new SimpleDateFormat(format).parse(str);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
