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
import otus.library.domain.Genre;
import otus.library.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
@ActiveProfiles("test")
@DisplayName("Тест контроллера жанров")
public class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Проверка страницы /genres")
    public void genreListPageTest() throws Exception {
        Genre genre = new Genre("testGenre");
        List<Genre> allGenres = Arrays.asList(genre);

        given(genreRepository.findAll()).willReturn(allGenres);

        mvc.perform(get("/genres").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/list"))
                .andExpect(model().attribute("genres", allGenres));
    }

    @Test
    @DisplayName("Проверка страницы /genres/edit")
    public void genreEditTest() throws Exception {
        String str = "testId";
        String strName = "testName";
        Optional<Genre> genre = Optional.ofNullable(new Genre(strName));

        given(genreRepository.findById(str)).willReturn(genre);

        mvc.perform(get("/genres/edit")
                .contentType(MediaType.TEXT_HTML)
                .param("id", str))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/edit"))
                .andExpect(model().attribute("genres", genre.get()));
    }

    @Test
    @DisplayName("Проверка страницы /genres/create")
    public void genreCreateTest() throws Exception {
        String strName = "testName";
        Genre genre = new Genre(strName);

        given(genreRepository.save(new Genre(strName))).willReturn(genre);

        mvc.perform(post("/genres/create")
                .contentType(MediaType.TEXT_HTML)
                .param("name", strName))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/genres"));
    }

    @Test
    @DisplayName("Проверка страницы /genres/delete")
    public void genreDeleteTest() throws Exception {
        String str = "testId";

        mvc.perform(post("/genres/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", str))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/genres"));
    }

    @Test
    @DisplayName("Проверка страницы /genres/new")
    public void genreNewTest() throws Exception {
        mvc.perform(get("/genres/new").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/new"));
    }

    @Test
    @DisplayName("Проверка страницы /genres/save")
    public void genreSaveTest() throws Exception {
        String str = "testId";
        String strName = "testName";
        Optional<Genre> genre = Optional.ofNullable(new Genre(strName));

        given(genreRepository.findById(str)).willReturn(genre);

        mvc.perform(post("/genres/save")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", str)
                    .param("name", strName))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/genres"));
    }
}
