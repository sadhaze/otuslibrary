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
import otus.library.domain.Author;

import java.util.Iterator;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")
@DisplayName("Тест DAO авторов")
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void dbWiper(){
        authorRepository.deleteAll();
        authorRepository.save(new Author(0L, "fn1", "ln1"));
        authorRepository.save(new Author(1L, "fn2", "ln2"));
        authorRepository.save(new Author(2L, "fn3", "ln3"));
    }

    @Test
    @DisplayName("Получение количества авторов")
    void authorDaoCountTest(){
        Assertions.assertEquals(3L, authorRepository.count());
    }

    @Test
    @DisplayName("Получение списка авторов")
    void authorDaoGetAllTest(){
        Iterator<Author> iterator = authorRepository.findAll().iterator();
        Assertions.assertEquals("fn1", iterator.next().getFname());
        Assertions.assertEquals("fn2", iterator.next().getFname());
        Assertions.assertEquals("fn3", iterator.next().getFname());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void authorDaoInsertAndGetByIdTest(){
        Assertions.assertEquals("fn1", authorRepository.findById(0L).get().getFname());
        Assertions.assertEquals("ln1", authorRepository.findById(0L).get().getLname());
    }
}
