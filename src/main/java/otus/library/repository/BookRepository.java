package otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.library.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByAuthorId(String id);
    List<Book> findAllByGenreId(String id);
}
