package otus.library.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import otus.library.domain.User;
import otus.library.repository.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@DisplayName("Тест контроллера пользователей")
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    private final String str = "testUser";
    private final User user = new User(str);

    @Test
    @DisplayName("Проверка страницы /api/users")
    public void userListPageTest() throws Exception {
        List<User> allUsers = Arrays.asList(user);

        given(userRepository.findAll()).willReturn(allUsers);

        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(str)));
    }

    @Test
    @DisplayName("Проверка страницы создания /api/users/testUser")
    public void userCreateTest() throws Exception {
        given(userRepository.save(any())).willReturn(user);

        mvc.perform(post("/api/users/" + str))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(str)));
    }

    @Test
    @DisplayName("Проверка страницы удаления /api/users/testUser")
    public void userDeleteTest() throws Exception {
        mvc.perform(delete("/api/users/" + str))
                .andExpect(status().isOk());
    }
}
