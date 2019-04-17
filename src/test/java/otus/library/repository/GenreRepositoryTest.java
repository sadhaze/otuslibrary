package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Genre;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@DisplayName("Тест DAO жанров")
public class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    @Test
    @DisplayName("Получение количества жанров")
    void genreDaoCountTest(){
        Assertions.assertEquals("3", genreRepository.count().toString());
    }

    @Test
    @DisplayName("Получение списка жанров")
    void genreDaoGetAllTest(){
        Assertions.assertEquals("Novel", genreRepository.getAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение жанра")
    void genreDaoInsertAndGetByIdTest(){
        Long id = new Long(10);
        genreRepository.insert(new Genre(id, "genretest"));
        Assertions.assertEquals("genretest", genreRepository.getById(id).getName());
    }
}
