package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Тест жанров")
class GenreTest {
    Genre genre = new Genre(1, "NameTest");

    @Test
    @DisplayName("Получение идентификатора")
    void GanreIdTest(){
        Assertions.assertEquals("1", genre.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void GanreNameTest(){
        Assertions.assertEquals("NameTest", genre.getName());
    }
}
