package ali.su.cft2j02.datasaver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String fio;

    public Users(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }
}
