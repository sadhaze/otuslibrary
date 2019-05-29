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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @DisplayName("Проверка страницы /users")
    public void userListPageTest() throws Exception {
        List<User> allUsers = Arrays.asList(user);

        given(userRepository.findAll()).willReturn(allUsers);

        mvc.perform(get("/users").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("users/list"))
                .andExpect(model().attribute("users", allUsers));
    }

    @Test
    @DisplayName("Проверка страницы /users/create")
    public void userCreateTest() throws Exception {
        given(userRepository.save(new User(str))).willReturn(user);

        mvc.perform(post("/users/create")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", str))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/users"));
    }

    @Test
    @DisplayName("Проверка страницы /users/delete")
    public void userDeleteTest() throws Exception {
        mvc.perform(post("/users/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", str))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/users"));
    }

    @Test
    @DisplayName("Проверка страницы /users/new")
    public void userNewTest() throws Exception {
        mvc.perform(get("/users/new").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("users/new"));
    }
}
