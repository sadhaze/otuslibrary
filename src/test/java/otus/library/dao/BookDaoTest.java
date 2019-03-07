package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.library.domain.Book;

@SpringBootTest
@DisplayName("Тест DAO книг")
public class BookDaoTest {
    @Autowired
    BookDao bookDao;

    @Test
    @DisplayName("Получение количества книг")
    void BookDaoCountTesе(){
        Assertions.assertEquals("4", bookDao.count().toString());
    }

    @Test
    @DisplayName("Получение списка книг")
    void BookDaoGetAllTest(){
        Assertions.assertEquals("Poem", bookDao.getAll());
    }

    @Test
    @DisplayName("Вставка и получение книги")
    void BookDaoInsertAndGetByIdTest(){
        bookDao.insert(new Book(10, "booktest", 1, 1));
        Assertions.assertEquals("booktest", bookDao.getById(10).getName());
        Assertions.assertEquals("1", bookDao.getById(10).getAuthor().toString());
        Assertions.assertEquals("1", bookDao.getById(10).getGenre().toString());
    }
}
