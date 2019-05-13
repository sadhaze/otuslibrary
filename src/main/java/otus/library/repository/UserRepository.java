package otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.library.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
