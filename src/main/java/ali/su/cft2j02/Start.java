package ali.su.cft2j02;

import ali.su.cft2j02.datareader.DataFileReader;
import ali.su.cft2j02.datareader.DataReader;
import ali.su.cft2j02.datasaver.TestTableRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "ali.su.cft2j02")
public class Start {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Start.class);
        ctx.getBean(DataFileReader.class).get().forEach(System.out::println);

        TestTableRepo ttRepo = ctx.getBean(TestTableRepo.class);
        ttRepo.findAll().forEach(System.out::println);
    }
}
