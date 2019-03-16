package otus.library.dao;

import otus.library.domain.Genre;
import java.util.List;

public interface GenreDao {
    Long count();
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
}
