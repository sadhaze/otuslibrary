package otus.library.repository;

import otus.library.domain.Genre;
import java.util.List;

public interface GenreRepository {
    Long count();
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
}
