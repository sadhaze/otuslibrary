package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Author;
import java.util.Iterator;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Тест DAO авторов")
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void dbWiper(){
        authorRepository.save(new Author("fn1", "ln1"));
        authorRepository.save(new Author("fn2", "ln2"));
        authorRepository.save(new Author("fn3", "ln3"));
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение количества авторов")
    void authorDaoCountTest(){
        Assertions.assertEquals(3L, authorRepository.count());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение списка авторов")
    void authorDaoGetAllTest(){
        Iterator<Author> iterator = authorRepository.findAll().iterator();
        Assertions.assertEquals("fn1", iterator.next().getFname());
        Assertions.assertEquals("fn2", iterator.next().getFname());
        Assertions.assertEquals("fn3", iterator.next().getFname());
    }

    @Test
    @DirtiesContext
    @DisplayName("Вставка и получение автора")
    void authorDaoInsertAndGetByIdTest(){
        Iterator<Author> iterator = authorRepository.findAll().iterator();
        String id = iterator.next().getId();
        Assertions.assertEquals("fn1", authorRepository.findById(id).get().getFname());
        Assertions.assertEquals("ln1", authorRepository.findById(id).get().getLname());
    }
}
