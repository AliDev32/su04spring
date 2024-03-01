package ali.su.cft2j02.datareader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    @Qualifier("dataLocation")
    String dataLocation() {
        return "C:\\tor\\workspace\\java\\works_bc_cursCFT2J_02\\su04spring\\src\\main\\resources";
    }

    @Bean
    @Qualifier("dateFormat")
    String dateFormat() {
        return "dd/MM/yyyy HH:mm:ss";
    }
}
