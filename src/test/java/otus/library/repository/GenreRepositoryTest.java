package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Genre;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Тест DAO жанров")
public class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    @Test
    @DisplayName("Получение количества жанров")
    void genreDaoCountTest(){
        Assertions.assertEquals(3L, genreRepository.count());
    }

    @Test
    @DisplayName("Получение списка жанров")
    void genreDaoGetAllTest(){
        Assertions.assertEquals("TestNovel", genreRepository.findAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение жанра")
    void genreDaoInsertAndGetByIdTest(){
        Long id = 1000L;
        genreRepository.save(new Genre(id, "genretest"));
        Assertions.assertEquals("genretest", genreRepository.getOne(1000L).getName());
    }
}
