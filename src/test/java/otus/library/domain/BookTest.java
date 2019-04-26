package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Тест книг")
public class BookTest {
    Long id = 1L;
    Book book = new Book(
            id,
            "booktest",
            new Author(11L,
                    "testauthorfname",
                    "testauthorlname"),
            new Genre(12L,
                    "testgenre"));

    @Test
    @DisplayName("Получение идентификатора")
    void bookIdTest(){
        Assertions.assertEquals("1", book.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void bookNameTest(){
        Assertions.assertEquals("booktest", book.getName());
    }

    @Test
    @DisplayName("Получение иидентификатора автора")
    void bookAutorIdTest(){
        Assertions.assertEquals("11", book.getAuthor().getId().toString());
    }

    @Test
    @DisplayName("Получение идентификатора жанра")
    void authorLNameTest(){
        Assertions.assertEquals("12", book.getGenre().getId().toString());
    }
}
