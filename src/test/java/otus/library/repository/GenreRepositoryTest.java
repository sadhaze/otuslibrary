package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Genre;
import java.util.Iterator;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Тест DAO жанров")
public class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    @BeforeEach
    void dbWiper(){
        genreRepository.save(new Genre("g1"));
        genreRepository.save(new Genre("g2"));
        genreRepository.save(new Genre("g3"));
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение количества жанров")
    void genreDaoCountTest(){
        Assertions.assertEquals(3L, genreRepository.count());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение списка жанров")
    void genreDaoGetAllTest(){
        Iterator<Genre> iterator = genreRepository.findAll().iterator();
        Assertions.assertEquals("g1", iterator.next().getName());
        Assertions.assertEquals("g2", iterator.next().getName());
        Assertions.assertEquals("g3", iterator.next().getName());
    }

    @Test
    @DirtiesContext
    @DisplayName("Вставка и получение жанра")
    void genreDaoInsertAndGetByIdTest(){
        Iterator<Genre> iterator = genreRepository.findAll().iterator();
        Assertions.assertEquals("g1", genreRepository.findById(iterator.next().getId()).get().getName());
    }
}
