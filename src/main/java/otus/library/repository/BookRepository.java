package otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import otus.library.domain.Book;

public interface BookRepository extends CrudRepository<Book, String> {
}
