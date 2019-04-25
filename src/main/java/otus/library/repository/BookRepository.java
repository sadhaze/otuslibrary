package otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
