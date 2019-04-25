package otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
