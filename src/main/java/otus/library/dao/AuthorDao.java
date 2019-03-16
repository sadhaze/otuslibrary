package otus.library.dao;

import otus.library.domain.Author;
import java.util.List;

public interface AuthorDao {
    Long count();
    void insert(Author author);
    Author getById(Long id);
    List<Author> getAll();
}
