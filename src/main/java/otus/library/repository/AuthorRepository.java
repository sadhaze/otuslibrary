package otus.library.repository;

import otus.library.domain.Author;
import java.util.List;

public interface AuthorRepository {
    Long count();
    void insert(Author author);
    Author getById(Long id);
    List<Author> getAll();
}
