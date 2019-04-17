package otus.library.repository;

import otus.library.domain.User;
import java.util.List;

public interface UserRepository {
    Long count();
    void insert(User user);
    User getById(String id);
    List<User> getAll();
}
