package ali.su.cft2j02.datasaver.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private long id;
    private String userName;
    private String fio;
}
