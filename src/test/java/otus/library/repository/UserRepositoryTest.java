package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.User;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Тест JPA пользователей")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Получение количества пользователей")
    void userJpaCountTest(){
        Assertions.assertEquals(1L, userRepository.count());
    }

    @Test
    @DisplayName("Получение списка пользователей")
    void userJpaGetAllTest(){
        Assertions.assertEquals("TestSuperAdmin", userRepository.findAll().get(0).getId());
    }

    @Test
    @DisplayName("Вставка и получение пользователя")
    void userJpaInsertAndGetByIdTest(){
        userRepository.save(new User("UserTest"));
        Assertions.assertEquals("UserTest", userRepository.getOne("UserTest").getId());
    }
}
