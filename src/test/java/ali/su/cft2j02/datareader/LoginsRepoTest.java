package ali.su.cft2j02.datareader;

import ali.su.cft2j02.datasaver.Login;
import ali.su.cft2j02.datasaver.LoginsRepo;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
public interface LoginsRepoTest extends LoginsRepo {
    List<Login> findAllByUserId(Long userId);
}
