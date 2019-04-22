package otus.library.repository;

import otus.library.domain.Book;
import java.util.List;

public interface BookRepository {
    Long count();
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
}
