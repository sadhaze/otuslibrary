package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.User;

import java.util.Iterator;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")
@DisplayName("Тест JPA пользователей")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void dbWiper(){
        userRepository.deleteAll();
        userRepository.save(new User("u1"));
        userRepository.save(new User("u2"));
        userRepository.save(new User("u3"));
    }

    @Test
    @DisplayName("Получение количества пользователей")
    void userJpaCountTest(){
        Assertions.assertEquals(3L, userRepository.count());
    }

    @Test
    @DisplayName("Получение списка пользователей")
    void userJpaGetAllTest(){
        Iterator<User> iterator = userRepository.findAll().iterator();
        Assertions.assertEquals("u1", iterator.next().getId());
        Assertions.assertEquals("u2", iterator.next().getId());
        Assertions.assertEquals("u3", iterator.next().getId());
    }

    @Test
    @DisplayName("Вставка и получение пользователя")
    void userJpaInsertAndGetByIdTest(){
        Assertions.assertEquals("u1", userRepository.findById("u1").get().getId());
    }
}
