package otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.library.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
