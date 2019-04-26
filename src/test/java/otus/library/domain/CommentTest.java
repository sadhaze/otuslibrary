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
@DisplayName("Тест комментариев")
public class CommentTest {
    Long id = 10L;
    Author author = new Author(id, "ftest", "ltest");
    Genre genre = new Genre(id, "TestGenre");
    Book book = new Book(id, "TestBook", author, genre);
    User user = new User("TestUser");
    String text = "TestComment";
    Comment comment = new Comment(id, book, user, text);

    @Test
    @DisplayName("Получение идентификатора")
    void commentIdTest(){
        Assertions.assertEquals("10", comment.getId().toString());
    }

    @Test
    @DisplayName("Получение идентификатора книги")
    void commentBookTest(){
        Assertions.assertEquals("10", comment.getBook().getId().toString());
    }

    @Test
    @DisplayName("Получение пользователя оставившего комментарий")
    void commentUserTest(){
        Assertions.assertEquals("TestUser", comment.getUser().getId());
    }

    @Test
    @DisplayName("Получение комментария")
    void commentTest(){
        Assertions.assertEquals("TestComment", comment.getComment());
    }
}
