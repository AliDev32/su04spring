package ali.su.cft2j02;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataFileReader implements DataReader<List<Data>> {
    private final String dataLocation;
    private final MapperSourceToData<String, Data> mapper;

    public DataFileReader(@Qualifier("dataLocation") String dataLocation, MapperSourceToData<String, Data> mapper) {
        this.dataLocation = dataLocation;
        this.mapper = mapper;
    }

    @Override
    public List<Data> get() {
        Scanner scanner = null;
        List<Data> res = new ArrayList<>();
        try {
            var file = new File(dataLocation);
            for (var f : file.listFiles()) {
                scanner = new Scanner(f);
                while (scanner.hasNext()) {
                    res.add(mapper.apply(scanner.nextLine()));
                }
            }
            res.forEach(System.out::println);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

/*
    public String getDataLocation() {
        return dataLocation;
    }
*/
}
