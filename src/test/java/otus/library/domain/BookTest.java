package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Тест книг")
public class BookTest {
    Book book = new Book(1, "booktest", 1, 1);

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
        Assertions.assertEquals("1", book.getAuthor().toString());
    }

    @Test
    @DisplayName("Получение идентификатора жанра")
    void AuthorLNameTest(){
        Assertions.assertEquals("1", book.getGenre().toString());
    }
}
