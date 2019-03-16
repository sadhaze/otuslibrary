package otus.library.dao;

import otus.library.domain.Book;
import java.util.List;

public interface BookDao {
    Long count();
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
}
