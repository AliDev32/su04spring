package ali.su.cft2j02;

import ali.su.cft2j02.datareader.DataFileReader;
import ali.su.cft2j02.datareader.DataReader;
import ali.su.cft2j02.datasaver.*;
import ali.su.cft2j02.middleworks.MiddleWorksRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = "ali.su.cft2j02")
public class Starter {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Starter.class);

        //чтение данных
        var data = (List<Data>) ctx.getBean(DataReader.class).get();

        //промежуточные обработки
        var workRunner = ctx.getBean(MiddleWorksRunner.class);
        data = workRunner.doWork(data);

        //сохранение данных
        var saver = ctx.getBean(DataSaver.class);
        saver.accept(data);
    }
}
