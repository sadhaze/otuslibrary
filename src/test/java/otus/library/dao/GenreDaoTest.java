package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import otus.library.domain.Genre;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Тест DAO жанров")
public class GenreDaoTest {
    @Autowired
    GenreDao genreDao;

    @Test
    @DisplayName("Получение количества жанров")
    void GanreDaoCountTesе(){
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
        genreDao.insert(new Genre(10, "genretest"));
        Assertions.assertEquals("genretest", genreDao.getById(10).getName());
    }
}
