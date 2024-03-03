package ali.su.cft2j02.datasaver.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Logins {
    @Id
    private long id;
    private Date accessDate;
    private long userId;
    private String application;

}
