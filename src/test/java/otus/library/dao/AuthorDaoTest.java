package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Author;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Тест DAO авторов")
public class AuthorDaoTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    @DisplayName("Получение количества авторов")
    void AuthorDaoCountTest(){
        Assertions.assertEquals("3", authorDao.count().toString());
    }

    @Test
    @DisplayName("Получение списка авторов")
    void AuthorDaoGetAllTest(){
        Assertions.assertEquals("Alex", authorDao.getAll().get(1).getFname());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void AuthorDaoInsertAndGetByIdTest(){
        Long id = new Long(10);
        authorDao.insert(new Author(id, "authorFNameTest", "authorLNameTest"));
        Assertions.assertEquals("authorFNameTest", authorDao.getById(id).getFname());
        Assertions.assertEquals("authorLNameTest", authorDao.getById(id).getLname());
    }
}
