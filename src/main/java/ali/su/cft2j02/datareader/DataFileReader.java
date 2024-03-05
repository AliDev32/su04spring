package ali.su.cft2j02.datareader;

import ali.su.cft2j02.Data;
import org.springframework.beans.factory.annotation.Value;
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
            @Value("${data-files.location}") String dataLocation, MapperSourceToData<String, Data> mapper
                         ) {
        this.dataLocation = dataLocation;
        this.mapper = mapper;
    }

    @Override
    public List<Data> get() {
        var res = new ArrayList<Data>();
        var folder = new File(dataLocation);
        var files = folder.listFiles();

        if (files == null) {return res;}

        for (var f : files) {
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNextLine()) {
                    //TODO: сделать выбор маппера по типу файла из мапы мапперов
                    res.add(mapper.apply(scanner.nextLine(), f.getName()));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }
}