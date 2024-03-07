package ali.su.cft2j02.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "data-files")
public class ReaderConfig {
    private String location;
    private String dateFormat;

    void setLocation(String location) {
        this.location = location;
    }

    void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getLocation() {
        return location;
    }

    public String getDateFormat() {
        return dateFormat;
    }

}
