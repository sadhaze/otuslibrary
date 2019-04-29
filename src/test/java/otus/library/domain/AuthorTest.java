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
@DisplayName("Тест автора")
class AuthorTest {
    Long id = 1L;
    Author author = new Author(id, "fnametest", "lnametest");

    @Test
    @DisplayName("Получение идентификатора")
    void authorIdTest(){
        Assertions.assertEquals("1", author.getId().toString());
    }

    @Test
    @DisplayName("Получение имени")
    void authorFNameTest(){
        Assertions.assertEquals("fnametest", author.getFname());
    }

    @Test
    @DisplayName("Получение фамилии")
    void authorLNameTest(){
        Assertions.assertEquals("lnametest", author.getLname());
    }
}
