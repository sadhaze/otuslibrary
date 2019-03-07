package otus.library.dao;

import otus.library.domain.Genre;
import java.util.List;

public interface GenreDao {
    Integer count();
    void insert(Genre genre);
    Genre getById(Integer id);
    List<Genre> getAll();
}
