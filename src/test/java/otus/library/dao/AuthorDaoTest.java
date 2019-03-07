package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.library.domain.Author;

@SpringBootTest
@DisplayName("Тест DAO авторов")
public class AuthorDaoTest {
    @Autowired
    AuthorDao authorDao;

    @Test
    @DisplayName("Получение количества авторов")
    void AuthorDaoCountTesе(){
        Assertions.assertEquals("4", authorDao.count().toString());
    }

    @Test
    @DisplayName("Получение списка авторов")
    void AuthorDaoGetAllTest(){
        Assertions.assertEquals("Poem", authorDao.getAll());
    }

    @Test
    @DisplayName("Вставка и получение автора")
    void AuthorDaoInsertAndGetByIdTest(){
        authorDao.insert(new Author(10, "authorFNameTest", "authorLNameTest"));
        Assertions.assertEquals("authorFNameTest", authorDao.getById(10).getFname());
        Assertions.assertEquals("authorLNameTest", authorDao.getById(10).getLname());
    }
}
