package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import otus.library.domain.Book;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
        Assertions.assertEquals("Ruslan i Lyudmila", bookDao.getAll().get(1).getName());
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
