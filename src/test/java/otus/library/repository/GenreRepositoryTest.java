package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Genre;

import java.util.Iterator;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")
@DisplayName("Тест DAO жанров")
public class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    @BeforeEach
    void dbWiper(){
        genreRepository.deleteAll();
        genreRepository.save(new Genre(0L, "g1"));
        genreRepository.save(new Genre(1L, "g2"));
        genreRepository.save(new Genre(2L, "g3"));
    }

    @Test
    @DisplayName("Получение количества жанров")
    void genreDaoCountTest(){
        Assertions.assertEquals(3L, genreRepository.count());
    }

    @Test
    @DisplayName("Получение списка жанров")
    void genreDaoGetAllTest(){
        Iterator<Genre> iterator = genreRepository.findAll().iterator();
        Assertions.assertEquals("g1", iterator.next().getName());
        Assertions.assertEquals("g2", iterator.next().getName());
        Assertions.assertEquals("g3", iterator.next().getName());
    }

    @Test
    @DisplayName("Вставка и получение жанра")
    void genreDaoInsertAndGetByIdTest(){
        Assertions.assertEquals("g1", genreRepository.findById(0L).get().getName());
    }
}
