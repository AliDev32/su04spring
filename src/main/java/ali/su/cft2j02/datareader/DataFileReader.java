package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import ali.su.cft2j02.config.ReaderConfig;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataFileReader implements DataReader<List<Data>> {
    private final String dataLocation;
    private final MapperSourceToData<String, Data> mapper;

    public DataFileReader(
            ReaderConfig readerConfig, MapperSourceToData<String, Data> mapper
                         ) {
        this.dataLocation = readerConfig.getLocation();
        this.mapper = mapper;
    }

    @Override
    public List<Data> get() {
        var res = new ArrayList<Data>();
        var folder = new File(dataLocation);
        var files = folder.listFiles();

        if (files == null) {
            return res;
        }

        for (var f : files) {
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNextLine()) {
                    //TODO: сделать получение маппера из мапы мапперов по типу файла
                    var data = mapper.apply(scanner.nextLine(), f.getName());
                    if (data != null) {
                        res.add(data);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }
}