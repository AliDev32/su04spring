package ali.su.cft2j02;

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

        var data = ctx.getBean(DataFileReader.class).get();
        data.forEach(System.out::println);


        System.out.println("===============================");
        var workRunner = ctx.getBean(MiddleWorksRunner.class);
        workRunner.getWorkers().forEach(System.out::println);

        data = workRunner.doWork(data);
        System.out.println("===============================");

        var saver = ctx.getBean(DataDbSaver.class);
        saver.accept(data);
    }
}
