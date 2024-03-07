package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.middleworks.WorkCheckFIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WorkCheckFIOTest {
    private final WorkCheckFIO service = new WorkCheckFIO();

    private static final String LOGIN_NAME = "usr1";
    private static final String LAST_NAME_LO = "usr1LastName";
    private static final String FIRST_NAME_LO = "usr1FirstName";
    private static final String PATRONYMIC_NAME_LO = "usr1Patronymic";
    private static final String LAST_NAME_UP = "Usr1LastName";
    private static final String FIRST_NAME_UP = "Usr1FirstName";
    private static final String PATRONYMIC_NAME_UP = "Usr1Patronymic";
    private static final String LOGIN_DATE = "01/03/2024 13:49:33";
    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String FILE_NAME = "";
    private static final String APP_TYPE = "mobile";

    @Test
    @DisplayName("ФИО начинаются с большой буквы")
    public void convert_normFio() {
        List<Data> actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME_LO, FIRST_NAME_LO, PATRONYMIC_NAME_LO, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE, FILE_NAME));
        actualList = service.doWork(actualList);

        List<Data> expectedList = new ArrayList<>();
        expectedList.add(new Data(LOGIN_NAME, LAST_NAME_UP, FIRST_NAME_UP, PATRONYMIC_NAME_UP, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE, FILE_NAME));
        Assertions.assertEquals(expectedList, actualList);
    }
}
