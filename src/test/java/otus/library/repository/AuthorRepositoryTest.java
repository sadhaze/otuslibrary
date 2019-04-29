package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Author;

@DataJpaTest
@ActiveProfiles("test")
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
        Assertions.assertEquals("TestAlex", authorRepository.findAll().get(1).getFname());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void authorDaoInsertAndGetByIdTest(){
        Long id = 10L;
        authorRepository.save(new Author(id, "authorFNameTest", "authorLNameTest"));
        Assertions.assertEquals("authorFNameTest", authorRepository.getOne(id).getFname());
        Assertions.assertEquals("authorLNameTest", authorRepository.getOne(id).getLname());
    }
}
