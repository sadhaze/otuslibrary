package otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
