package otus.library.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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
    private GenreRepository genreRepository;

    private final String strName = "testGenre";
    private final Genre genre = new Genre(strName);

    @Test
    @DisplayName("Проверка страницы /genres")
    public void genreListPageTest() throws Exception {
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
        Optional<Genre> optionalGenre = Optional.ofNullable(genre);

        given(genreRepository.findById("testId")).willReturn(optionalGenre);

        mvc.perform(get("/genres/edit")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/edit"))
                .andExpect(model().attribute("genres", optionalGenre.get()));
    }

    @Test
    @DisplayName("Проверка страницы /genres/create")
    public void genreCreateTest() throws Exception {
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
        mvc.perform(post("/genres/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "testId"))
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
        Optional<Genre> optionalGenre = Optional.ofNullable(genre);

        given(genreRepository.findById("testId")).willReturn(optionalGenre);

        mvc.perform(post("/genres/save")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId")
                    .param("name", strName))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/genres"));
    }
}
