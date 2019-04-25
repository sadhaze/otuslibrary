package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.library.domain.Author;

@DataJpaTest
@DisplayName("Тест DAO авторов")
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("Получение количества авторов")
    void authorDaoCountTest(){
        Assertions.assertEquals(3L, authorRepository.count());
    }

    @Test
    @DisplayName("Получение списка авторов")
    void authorDaoGetAllTest(){
        Assertions.assertEquals("Alex", authorRepository.findAll().get(1).getFname());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void authorDaoInsertAndGetByIdTest(){
        Long id = new Long(10);
        authorRepository.save(new Author(id, "authorFNameTest", "authorLNameTest"));
        Assertions.assertEquals("authorFNameTest", authorRepository.getOne(id).getFname());
        Assertions.assertEquals("authorLNameTest", authorRepository.getOne(id).getLname());
    }
}
