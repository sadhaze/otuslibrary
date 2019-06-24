package otus.library.rest;

import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private final String genreId = "testId";
    private Genre genre = new Genre(strName);

    @BeforeEach
    public void init(){
        genre.setName(strName);
        genre.setId(genreId);
    }

    @Test
    @DisplayName("Проверка страницы /api/genres")
    public void genreListPageTest() throws Exception {
        List<Genre> allGenres = Arrays.asList(genre);

        given(genreRepository.findAll()).willReturn(allGenres);

        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(strName)));
    }

    @Test
    @DisplayName("Проверка редактирования имени жанра /api/genres/{id}/name/{name}")
    public void genreEditTest() throws Exception {
        final String newName = "newName";
        Optional<Genre> optionalGenre = Optional.ofNullable(genre);
        given(genreRepository.findById(genreId)).willReturn(optionalGenre);
        genre.setName(newName);
        given(genreRepository.save(genre)).willReturn(genre);

        mvc.perform(put("/api/genres/" + genre.getId() + "/name/" + newName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(newName)));
    }

    @Test
    @DisplayName("Проверка страницы создание /api/genres/name/{name}")
    public void genreCreateTest() throws Exception {
        given(genreRepository.save(any())).willReturn(genre);

        mvc.perform(post("/api/genres/name/" + strName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(strName)));
    }

    @Test
    @DisplayName("Проверка страницы /api/genres/{id}")
    public void genreDeleteTest() throws Exception {
        mvc.perform(delete("/api/genres/" + genre.getId()))
                .andExpect(status().isOk());
        verify(genreRepository).deleteById(genreId);
    }
}
