package ali.su.cft2j02;

import ali.su.cft2j02.datareader.DataFileReader;
import ali.su.cft2j02.datasaver.Users;
import ali.su.cft2j02.datasaver.UsersRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ali.su.cft2j02")
public class Start {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Start.class);
        ctx.getBean(DataFileReader.class).get().forEach(System.out::println);

        UsersRepo usersRepo = ctx.getBean(UsersRepo.class);
        usersRepo.save(new Users("uLogin001", "Xxx Yyy Zzz"));
    }
}
