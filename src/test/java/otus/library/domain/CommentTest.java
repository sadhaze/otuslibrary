package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Тест комментариев")
public class CommentTest {
    Author author = new Author("ftest", "ltest");
    Genre genre = new Genre("TestGenre");
    Book book = new Book("TestBook", author, genre);
    User user = new User("TestUser");
    String text = "TestComment";
    Comment comment = new Comment(book, user, text);

    @BeforeEach
    void setId(){
        book.setId("10");
        comment.setId("1");
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора")
    void commentIdTest(){
        Assertions.assertEquals("1", comment.getId());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора книги")
    void commentBookTest(){
        Assertions.assertEquals("10", comment.getBook().getId());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение пользователя оставившего комментарий")
    void commentUserTest(){
        Assertions.assertEquals("TestUser", comment.getUser().getId());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение комментария")
    void commentTest(){
        Assertions.assertEquals("TestComment", comment.getComment());
    }
}
