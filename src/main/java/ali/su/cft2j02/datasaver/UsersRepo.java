package ali.su.cft2j02.datasaver;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
