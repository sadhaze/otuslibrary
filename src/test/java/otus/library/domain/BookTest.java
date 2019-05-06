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
@DisplayName("Тест книг")
public class BookTest {
    Book book = new Book("booktest",
            new Author("testauthorfname",
                    "testauthorlname"),
            new Genre("testgenre"));

    @BeforeEach
    void setId(){
        book.setId("1");
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора")
    void bookIdTest(){
        Assertions.assertEquals("1", book.getId().toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение имени")
    void bookNameTest(){
        Assertions.assertEquals("booktest", book.getName());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение иидентификатора автора")
    void bookAutorIdTest(){
        Assertions.assertEquals("11", book.getAuthor().getId().toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора жанра")
    void authorLNameTest(){
        Assertions.assertEquals("12", book.getGenre().getId().toString());
    }
}
