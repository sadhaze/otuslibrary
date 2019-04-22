package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.User;

@DataJpaTest
@Import(UserRepositoryJpa.class)
@DisplayName("Тест JPA пользователей")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Получение количества пользователей")
    void userJpaCountTest(){
        Assertions.assertEquals("1", userRepository.count().toString());
    }

    @Test
    @DisplayName("Получение списка пользователей")
    void userJpaGetAllTest(){
        Assertions.assertEquals("SuperAdmin", userRepository.getAll().get(0).getId());
    }

    @Test
    @DisplayName("Вставка и получение пользователя")
    void userJpaInsertAndGetByIdTest(){
        userRepository.insert(new User("UserTest"));
        Assertions.assertEquals("UserTest", userRepository.getById("UserTest").getId());
    }
}
