package otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import otus.library.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {
}
