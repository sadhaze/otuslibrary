package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.User;
import java.util.Iterator;

@DataMongoTest
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
    @DirtiesContext
    @DisplayName("Получение количества пользователей")
    void userJpaCountTest(){
        Assertions.assertEquals(3L, userRepository.count());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение списка пользователей")
    void userJpaGetAllTest(){
        Iterator<User> iterator = userRepository.findAll().iterator();
        Assertions.assertEquals("u1", iterator.next().getId());
        Assertions.assertEquals("u2", iterator.next().getId());
        Assertions.assertEquals("u3", iterator.next().getId());
    }

    @Test
    @DirtiesContext
    @DisplayName("Вставка и получение пользователя")
    void userJpaInsertAndGetByIdTest(){
        Assertions.assertEquals("u1", userRepository.findById("u1").get().getId());
    }
}
