package ali.su.cft2j02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext("ali.su.cft2j02");
//        System.out.println(ctx.getBean(DataReader.class).getDataLocation());
        System.out.println(ctx.getBean(DataReader.class).get());
    }
}
