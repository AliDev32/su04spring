package ali.su.cft2j02;

import ali.su.cft2j02.config.ReaderConfig;
import ali.su.cft2j02.datareader.DataFileReader;
import ali.su.cft2j02.datasaver.*;
import ali.su.cft2j02.middleworks.MiddleWorksRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "ali.su.cft2j02")
public class Starter {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Starter.class);

        //чтение данных
        var data = ctx.getBean(DataFileReader.class).get();

        //промежуточные обработки
        var workRunner = ctx.getBean(MiddleWorksRunner.class);
        data = workRunner.doWork(data);

        //сохранение данных
        var saver = ctx.getBean(DataDbSaver.class);
        saver.accept(data);
    }
}
