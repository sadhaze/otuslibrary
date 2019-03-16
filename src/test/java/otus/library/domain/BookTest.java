package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Тест книг")
public class BookTest {
    Long id = new Long(1);
    Book book = new Book(
            id,
            "booktest",
            new Author(new Long(11),
                    "testauthorfname",
                    "testauthorlname"),
            new Genre(new Long(12),
                    "testgenre"));

    @Test
    @DisplayName("Получение идентификатора")
    void BookIdTest(){
        Assertions.assertEquals("1", book.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void BookNameTest(){
        Assertions.assertEquals("booktest", book.getName());
    }

    @Test
    @DisplayName("Получение иидентификатора автора")
    void BookAutorIdTest(){
        Assertions.assertEquals("11", book.getAuthor().getId().toString());
    }

    @Test
    @DisplayName("Получение идентификатора жанра")
    void AuthorLNameTest(){
        Assertions.assertEquals("12", book.getGenre().getId().toString());
    }
}
