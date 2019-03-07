package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Тест автора")
class AuthorTest {
    Author author = new Author(1, "fnametest", "lnametest");

    @Test
    @DisplayName("Получение идентификатора")
    void AuthorIdTest(){
        Assertions.assertEquals("1", author.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void AuthorFNameTest(){
        Assertions.assertEquals("fnametest", author.getFname());
    }

    @Test
    @DisplayName("Получение фамилии")
    void AuthorLNameTest(){
        Assertions.assertEquals("lnametest", author.getLname());
    }
}
