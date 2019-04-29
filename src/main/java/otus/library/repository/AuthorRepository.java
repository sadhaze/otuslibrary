package otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import otus.library.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
