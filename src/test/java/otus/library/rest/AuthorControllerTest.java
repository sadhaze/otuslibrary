package otus.library.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import otus.library.domain.Author;
import otus.library.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
@ActiveProfiles("test")
@DisplayName("Тест контроллера авторов")
public class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private AuthorRepository authorRepository;

    private final String strFname = "testFname";
    private final String strLname = "testLname";
    private final Author author = new Author(strFname, strLname);

    @Test
    @DisplayName("Проверка страницы /authors")
    public void authorListPageTest() throws Exception {
        List<Author> allAuthors = Arrays.asList(author);

        given(authorRepository.findAll()).willReturn(allAuthors);

        mvc.perform(get("/authors").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/list"))
                .andExpect(model().attribute("authors", allAuthors));
    }

    @Test
    @DisplayName("Проверка страницы /authors/edit")
    public void authorEditTest() throws Exception {
        Optional<Author> optionalAuthor = Optional.ofNullable(author);

        given(authorRepository.findById(str)).willReturn(optionalAuthor);

        mvc.perform(get("/authors/edit")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/edit"))
                .andExpect(model().attribute("authors", optionalAuthor.get()));
    }

    @Test
    @DisplayName("Проверка страницы /authors/create")
    public void authorCreateTest() throws Exception {
        given(authorRepository.save(new Author(strFname, strLname))).willReturn(author);

        mvc.perform(post("/authors/create")
                    .contentType(MediaType.TEXT_HTML)
                    .param("fname", strFname)
                    .param("lname", strLname))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/authors"));
    }

    @Test
    @DisplayName("Проверка страницы /authors/delete")
    public void authorDeleteTest() throws Exception {
        mvc.perform(post("/authors/delete")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/authors"));
    }

    @Test
    @DisplayName("Проверка страницы /authors/new")
    public void authorNewTest() throws Exception {
        mvc.perform(get("/authors/new").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/new"));
    }

    @Test
    @DisplayName("Проверка страницы /authors/save")
    public void authorSaveTest() throws Exception {
        Optional<Author> optionalAuthor = Optional.ofNullable(author);

        given(authorRepository.findById("testId")).willReturn(optionalAuthor);

        mvc.perform(post("/authors/save")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId")
                    .param("fname", strFname)
                    .param("lname", strLname))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/authors"));
    }
}
