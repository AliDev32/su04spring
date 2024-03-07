package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Starter;
import ali.su.cft2j02.config.ReaderConfig;
import ali.su.cft2j02.datasaver.*;
import ali.su.cft2j02.middleworks.MiddleWorksRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest(classes = {Starter.class})
public class IntegrationTest {
    @Autowired
    private DataFileReader dataReader;
    @Autowired
    private MiddleWorksRunner middleWorksRunner;
    @Autowired
    private DataDbSaver dataDbSaver;
    @Autowired
    private ReaderConfig readerConfig;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private LoginsRepoTest loginsRepo;

    private static final String LOGIN = "usrLogin1";
    private static final String LOGIN_DATE1 = "01/03/2024 13:49:33";
    private static final String LOGIN_DATE2 = "03/03/2024 08:08:08";
    private static final String APP_TYPE1 = "web";
    private static final String APP_TYPE2 = "mobile";
    private final String STR1 = "usrLogin1\tusr1LastName\tusr1FirstName\tusr1Patronymic\t" + LOGIN_DATE1 + "\t" + APP_TYPE1;
    private final String STR2 = "usrLogin1\tusr3LastName\tusr3FirstName\tusr3Patronymic\t" + LOGIN_DATE2 + "\t" + APP_TYPE2;

    @Test
    @DisplayName("Чтение данных из файла, обработка, запись в БД")
    public void file2DB_success() {
        deleteUserToDB(LOGIN);
        createFile(getFullFileName());

        //чтение данных из файла
        var data = dataReader.get();
        //промежуточные обработки данных
        data = middleWorksRunner.doWork(data);
        //сохранение данных в БД
        dataDbSaver.accept(data);

        User user = usersRepo.findByUsername(LOGIN);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(2, loginsRepo.findAllByUserId(user.getId()).size());
    }

    private void createFile(Path file) {
        String filePath = readerConfig.getLocation();
        try {
            deleteFile(file);

            if (!Files.exists(Path.of(filePath))) {
                Files.createDirectories(Path.of(filePath));
            }

            Files.writeString(file, STR1.concat("\n"), StandardOpenOption.CREATE);
            Files.writeString(file, STR2.concat("\n"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFullFileName() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
        final String fileName = formatter.format(new Date()).concat(".txt");
        return Path.of(readerConfig.getLocation().concat("/").concat(fileName));
    }

    private void deleteFile(Path file) {
        if (file == null) return;
        file.toFile().delete();
    }

    private void deleteUserToDB(String login) {
        User user = usersRepo.findByUsername(login);
        if (user != null) {
            try {
                List<Login> logins = loginsRepo.findAllByUserId(user.getId());
                loginsRepo.deleteAll(logins);
                usersRepo.deleteById(user.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
