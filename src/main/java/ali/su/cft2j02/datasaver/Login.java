package ali.su.cft2j02.datasaver;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date accessDate;
    private long userId;
    private String application;

    public Login(Date accessDate, long userId, String application) {
        this.accessDate = accessDate;
        this.userId = userId;
        this.application = application;
    }

    public Login() {
    }
}
