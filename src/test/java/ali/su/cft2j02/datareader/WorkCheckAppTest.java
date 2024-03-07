package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.middleworks.WorkCheckApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorkCheckAppTest {
    private final WorkCheckApp service = new WorkCheckApp();

    private static final String LOGIN_NAME = "usr1";
    private static final String LAST_NAME = "usr1LastName";
    private static final String FIRST_NAME = "usr1FirstName";
    private static final String PATRONYMIC_NAME = "usr1Patronymic";
    private static final String LOGIN_DATE = "01/03/2024 13:49:33";
    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String APP_TYPE1 = "mobile";
    private static final String APP_TYPE2 = "web";
    private static final String APP_TYPE3 = "pc";
    private static final String APP_TYPE4 = "other:pc";
    private static final String FILE_NAME = "";

    @Test
    @DisplayName("Тип приложения из зарегистрированного списка")
    public void typeAppl_ofRegisterList() {
        List<Data> actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE1, FILE_NAME));
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE2, FILE_NAME));
        actualList = service.doWork(actualList);
        actualList = actualList.stream().sorted(Comparator.comparing(Data::getAppType)).toList();

        List<Data> expectedList = new ArrayList<>();
        expectedList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE1, FILE_NAME));
        expectedList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE2, FILE_NAME));
        expectedList = expectedList.stream().sorted(Comparator.comparing(Data::getAppType)).toList();

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Тип приложения не из зарегистрированного списка")
    public void typeAppl_NoOfRegisterList() {
        List<Data> actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE3, FILE_NAME));
        actualList = service.doWork(actualList);

        final List<Data> expectedList = new ArrayList<>();
        expectedList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE4, FILE_NAME));

        Assertions.assertEquals(expectedList, actualList);
    }
}
