package otus.library.dao;

import otus.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author author);
    Author getById(int id);
    List<Author> getAll();
}
