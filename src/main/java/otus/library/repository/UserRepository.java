package otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import otus.library.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
}
