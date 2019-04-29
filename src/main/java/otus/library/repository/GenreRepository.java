package otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
