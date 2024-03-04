package ali.su.cft2j02.datasaver;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String fio;
//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private List<Login> logins;

    public User(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
