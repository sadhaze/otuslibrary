package otus.library.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Тест юзера")
public class UserTest {
    String id = "dummy";
    User user = new User("TestUser");

    @Test
    @DirtiesContext
    @DisplayName("Получение идентификатора")
    void userIdTest(){
        Assertions.assertEquals("TestUser", user.getId());
    }
}
