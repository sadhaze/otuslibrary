package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Author;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@DisplayName("Тест DAO авторов")
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("Получение количества авторов")
    void authorDaoCountTest(){
        Assertions.assertEquals("3", authorRepository.count().toString());
    }

    @Test
    @DisplayName("Получение списка авторов")
    void authorDaoGetAllTest(){
        Assertions.assertEquals("Alex", authorRepository.getAll().get(1).getFname());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void authorDaoInsertAndGetByIdTest(){
        Long id = new Long(10);
        authorRepository.insert(new Author(id, "authorFNameTest", "authorLNameTest"));
        Assertions.assertEquals("authorFNameTest", authorRepository.getById(id).getFname());
        Assertions.assertEquals("authorLNameTest", authorRepository.getById(id).getLname());
    }
}
