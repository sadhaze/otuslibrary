package otus.library.dao;

import otus.library.domain.Book;
import java.util.List;

public interface BookDao {
    Integer count();
    void insert(Book book);
    Book getById(Integer id);
    List<Book> getAll();
}
