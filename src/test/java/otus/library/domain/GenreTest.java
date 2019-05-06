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
@DisplayName("Тест жанров")
class GenreTest {
    Genre genre = new Genre("NameTest");

    @BeforeEach
    void setId(){
        genre.setId("1");
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора")
    void genreIdTest(){
        Assertions.assertEquals("1", genre.getId().toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение имени")
    void genreNameTest(){
        Assertions.assertEquals("NameTest", genre.getName());
    }
}
