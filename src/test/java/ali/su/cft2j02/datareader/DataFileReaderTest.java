package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.config.LogConfig;
import ali.su.cft2j02.config.ReaderConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DataFileReaderTest {
    private final DataFileReader dataFileReader;
    private final ReaderConfig readerConfig;
    private final Path inFile;
    private final MapTabDelimitedStringToData mapper;

    private final String STR1 = "usr1\tusr1LastName\tusr1FirstName\tusr1Patronymic\t01/03/2024 13:49:33\tweb";
    private final String STR2 = "usr3\tusr3LastName\tusr3FirstName\tusr3Patronymic\t01/03/2024 14:58:88\tmobile";
    private final String BAD_STR1 = "usr1";
    private final String BAD_STR2 = "usr3\tusr3LastName";
    private final String LOG_LOCATION = "C:/tor/workspace/java/works_bc_cursCFT2J_02/su04spring/test/logfiles/";
    private final String DATA_LOCATION = "C:/tor/workspace/java/works_bc_cursCFT2J_02/su04spring/test/datafiles/";
    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public DataFileReaderTest() {
        LogConfig logConfig = new LogConfig();
        logConfig.setLocation(LOG_LOCATION);
        readerConfig = new ReaderConfig();
        readerConfig.setLocation(DATA_LOCATION);
        readerConfig.setDateFormat(DATE_FORMAT);
        mapper = new MapTabDelimitedStringToData(readerConfig, logConfig);
        dataFileReader = new DataFileReader(readerConfig, mapper);
        inFile = getPath();
    }

    @Test
    @DisplayName("Успешное чтение данных из файла")
    public void load_success() {
        createFile(inFile);

        List<Data> actualList = dataFileReader.get();
        actualList.sort(Comparator.comparing(Data::getLoginDate));

        List<Data> expectedList = new ArrayList<>();
        expectedList.add(mapper.apply(STR1, inFile.toFile().getName()));
        expectedList.add(mapper.apply(STR2, inFile.toFile().getName()));
        expectedList.sort(Comparator.comparing(Data::getLoginDate));

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Чтение некорректных данных из файла")
    public void load_invalidData() {
        createInvalidFile(inFile);
        final List<Data> actualList = dataFileReader.get();

        Assertions.assertEquals(0, actualList.size());
    }

    @Test
    @DisplayName("Чтение данных из пустого каталога")
    public void load_emptyDir() {
        String filePath = readerConfig.getLocation();
        clearPath(filePath);
        final List<Data> actualList = dataFileReader.get();

        Assertions.assertEquals(0, actualList.size());
    }

    private void createFile(Path file) {
        String filePath = readerConfig.getLocation();
        try {
            clearPath(filePath);

            if (!Files.exists(Path.of(filePath))) {
                Files.createDirectories(Path.of(filePath));
            }

            Files.writeString(file, STR1.concat("\n"), StandardOpenOption.CREATE);
            Files.writeString(file, STR2.concat("\n"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createInvalidFile(Path file) {
        String filePath = readerConfig.getLocation();
        try {
            clearPath(filePath);

            if (!Files.exists(Path.of(filePath)))
                Files.createDirectories(Path.of(filePath));

            Files.writeString(file, BAD_STR1.concat("\n"), StandardOpenOption.CREATE);
            Files.writeString(file, BAD_STR2.concat("\n"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPath() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd'.txt'");
        final String fileName = formatter.format(new Date());
        return Path.of(readerConfig.getLocation().concat("/").concat(fileName));
    }

    private void clearPath(String pathName) {
        final File folder = new File(pathName);
        final File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            file.delete();
        }
    }
}
