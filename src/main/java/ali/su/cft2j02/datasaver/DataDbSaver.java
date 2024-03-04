package ali.su.cft2j02.datasaver;

import ali.su.cft2j02.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDbSaver implements DataSaver<List<Data>> {
    private final UsersRepo usersRepo;
    private final LoginsRepo loginsRepo;

    public DataDbSaver(UsersRepo usersRepo, LoginsRepo loginsRepo) {
        this.usersRepo = usersRepo;
        this.loginsRepo = loginsRepo;
    }

    @Override
    public void accept(List<Data> data) {
        data.forEach(d -> {
            User user = usersRepo.findByUsername(d.getLoginName());
            if (user == null) {
                user = usersRepo.save(new User(d.getLoginName(), d.getFio()));
            }
            loginsRepo.save(new Login(d.getLoginDate(), user.getId(), d.getAppType()));
        });
    }
}
