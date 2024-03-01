package ali.su.cft2j02;

import ali.su.cft2j02.datareader.DataFileReader;
import ali.su.cft2j02.datareader.DataReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext("ali.su.cft2j02");
        ctx.getBean(DataFileReader.class).get().forEach(System.out::println);
    }
}
