package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
