package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Genre;


@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("Тест DAO жанров")
public class GenreDaoTest {
    @Autowired
    GenreDao genreDao;

    @Test
    @DisplayName("Получение количества жанров")
    void GanreDaoCountTest(){
        Assertions.assertEquals("3", genreDao.count().toString());
    }

    @Test
    @DisplayName("Получение списка жанров")
    void GanreDaoGetAllTest(){
        Assertions.assertEquals("Novel", genreDao.getAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение жанра")
    void GanreDaoInsertAndGetByIdTest(){
        Long id = new Long(10);
        genreDao.insert(new Genre(id, "genretest"));
        Assertions.assertEquals("genretest", genreDao.getById(id).getName());
    }
}
