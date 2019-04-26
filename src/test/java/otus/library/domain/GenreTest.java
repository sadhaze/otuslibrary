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
@DisplayName("Тест жанров")
class GenreTest {
    Long id = 1L;
    Genre genre = new Genre(id, "NameTest");

    @Test
    @DisplayName("Получение идентификатора")
    void genreIdTest(){
        Assertions.assertEquals("1", genre.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void genreNameTest(){
        Assertions.assertEquals("NameTest", genre.getName());
    }
}
