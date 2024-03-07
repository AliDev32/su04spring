package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.config.LogConfig;
import ali.su.cft2j02.middleworks.WorkCheckLoginDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorkCheckLoginDateTest {
    private final WorkCheckLoginDate service;
    private final LogConfig logConfig = new LogConfig();

    private static final String LOGIN_NAME = "usr1";
    private static final String LAST_NAME = "usr1LastName";
    private static final String FIRST_NAME = "usr1FirstName";
    private static final String PATRONYMIC_NAME = "usr1Patronymic";
    private static final String LOGIN_DATE = "01/03/2024 13:49:33";
    private static final String APP_TYPE = "mobile";
    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String FILE_NAME = "";
    private final String LOG_LOCATION = "C:/tor/workspace/java/works_bc_cursCFT2J_02/su04spring/test/logfiles/";

    public WorkCheckLoginDateTest() {
        logConfig.setLocation(LOG_LOCATION);
        service = new WorkCheckLoginDate(logConfig);
    }

    @Test
    @DisplayName("Дата входа присутствует")
    public void accessDate_exist() {
        List<Data> actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE, FILE_NAME));
        actualList = service.doWork(actualList);

        List<Data> expectedList = new ArrayList<>();
        expectedList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE, FILE_NAME));

        Assertions.assertEquals(expectedList, actualList);
        Assertions.assertEquals(0, getLogFileCount(), "Запись не должна попасть в лог файл");
    }

    @Test
    @DisplayName("Дата входа отсутствует")
    public void accessDate_empty() {
        List<Data> actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, null, APP_TYPE, FILE_NAME));
        actualList = service.doWork(actualList);

        Assertions.assertEquals(0, actualList.size(), "Запись не должна попадать в выходной список");
        Assertions.assertEquals(1, getLogFileCount(), "Запись должна попасть в лог файл");
    }

    private int getLogFileCount() {
        final File folder = new File(logConfig.getLocation());
        final File[] files = folder.listFiles();

        if (files == null) return 0;
        return files.length;
    }

    @BeforeEach
    public void clearDir() {
        final File folder = new File(logConfig.getLocation());
        final File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            file.delete();
        }
    }
}
