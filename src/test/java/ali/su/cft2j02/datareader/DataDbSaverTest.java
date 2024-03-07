package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.datasaver.*;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.times;

public class DataDbSaverTest {
    private final DataDbSaver dataDbSaver;
    private final UsersRepo usersRepo = Mockito.mock(UsersRepo.class);
    private final LoginsRepo loginsRepo = Mockito.mock(LoginsRepo.class);

    private static final String LOGIN_NAME = "usr1";
    private static final String LAST_NAME = "usr1LastName";
    private static final String FIRST_NAME = "usr1FirstName";
    private static final String PATRONYMIC_NAME = "usr1Patronymic";
    private static final String FIO = LAST_NAME + " " + FIRST_NAME + " " + PATRONYMIC_NAME;
    private static final String LOGIN_DATE = "01/03/2024 13:49:33";
    private static final String APP_TYPE = "mobile";
    private static final String FILE_NAME = "";
    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private final List<Data> actualList;

    public DataDbSaverTest() {
        dataDbSaver = new DataDbSaver(usersRepo, loginsRepo);

        actualList = new ArrayList<>();
        actualList.add(new Data(LOGIN_NAME, LAST_NAME, FIRST_NAME, PATRONYMIC_NAME, Utils.mapStringToDate(LOGIN_DATE, DATE_FORMAT), APP_TYPE, FILE_NAME));
    }

    @BeforeEach
    public void starter() {
        Mockito.reset(usersRepo, loginsRepo);
    }

    @Test
    @DisplayName("Сохранение в БД - новый пользователь")
    public void saveToDB_newUser() {
        Mockito.doReturn(null).when(usersRepo).findByUsername(LOGIN_NAME); // пользователь не найден

        User user = new User(LOGIN_NAME, FIO);
        Mockito.doReturn(user).when(usersRepo).save(Mockito.any(User.class));  // создание нового пользователя

        dataDbSaver.accept(actualList);

        // Сохранение нового пользователя
        Mockito.verify(usersRepo, times(1)).save(Mockito.any(User.class));

        // Сохранение логина пользователя
        Mockito.verify(loginsRepo, times(1)).save(Mockito.any(Login.class));
    }

    @Test
    @DisplayName("Сохранение в БД - пользователь уже есть в БД")
    public void saveToDB_onlyLogin() {
        User user = new User(LOGIN_NAME, FIO);
        Mockito.doReturn(user).when(usersRepo).findByUsername(LOGIN_NAME); // пользователь найден в БД

        dataDbSaver.accept(actualList);

        // Отсутствует сохранение нового пользователя
        Mockito.verify(usersRepo, times(0)).save(user);

        // Сохранение логина пользователя
        Mockito.verify(loginsRepo, times(1)).save(Mockito.any(Login.class));
    }
}
