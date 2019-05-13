package otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.library.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
