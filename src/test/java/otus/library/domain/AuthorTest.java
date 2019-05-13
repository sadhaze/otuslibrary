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
@DisplayName("Тест автора")
class AuthorTest {
    Author author = new Author("fnametest", "lnametest");

    @BeforeEach
    void setId(){
        author.setId("1");
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора")
    void authorIdTest(){
        Assertions.assertEquals("1", author.getId());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение имени")
    void authorFNameTest(){
        Assertions.assertEquals("fnametest", author.getFname());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение фамилии")
    void authorLNameTest(){
        Assertions.assertEquals("lnametest", author.getLname());
    }
}
