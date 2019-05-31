package otus.library.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;
import otus.library.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@ActiveProfiles("test")
@DisplayName("Тест контроллера книг")
public class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookRepository bookRepository;

    private final String strName = "testBook";
    private final Author author = new Author("testFname", "testLname");
    private final Genre genre = new Genre("testGenre");
    private final Book book = new Book(strName, author, genre);

    @Test
    @DisplayName("Проверка страницы /books")
    public void bookListPageTest() throws Exception {
        List<Book> allBooks = Arrays.asList(book);

        given(bookRepository.findAll()).willReturn(allBooks);

        mvc.perform(get("/books").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attribute("books", allBooks));
    }

    @Test
    @DisplayName("Проверка страницы /books/edit")
    public void bookEditTest() throws Exception {
        String str = "testId";
        Optional<Book> optionalBook = Optional.ofNullable(book);

        given(bookRepository.findById(str)).willReturn(optionalBook);

        mvc.perform(get("/books/edit")
                .contentType(MediaType.TEXT_HTML)
                .param("id", str))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().attribute("books", optionalBook.get()));
    }

    @Test
    @DisplayName("Проверка страницы /books/create")
    public void bookCreateTest() throws Exception {
        Optional<Author> optionalAuthor = Optional.ofNullable(author);
        Optional<Genre> optionalGenre = Optional.ofNullable(genre);
        given(authorRepository.findById("authorid")).willReturn(optionalAuthor);
        given(genreRepository.findById("genreid")).willReturn(optionalGenre);
        given(bookRepository.save(book)).willReturn(book);

        mvc.perform(post("/books/create")
                    .contentType(MediaType.TEXT_HTML)
                    .param("name", strName)
                    .param("authorId", "authorid")
                    .param("genreId", "genreid"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/books"));
    }

    @Test
    @DisplayName("Проверка страницы /books/delete")
    public void bookDeleteTest() throws Exception {
         mvc.perform(post("/books/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/books"));
    }

    @Test
    @DisplayName("Проверка страницы /books/new")
    public void bookNewTest() throws Exception {
        mvc.perform(get("/books/new").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("books/new"));
    }

    @Test
    @DisplayName("Проверка страницы /books/save")
    public void bookSaveTest() throws Exception {
        Optional<Author> optionalAuthor = Optional.ofNullable(author);
        Optional<Genre> optionalGenre = Optional.ofNullable(genre);
        Optional<Book> optionalBook = Optional.ofNullable(book);
        given(authorRepository.findById("authorid")).willReturn(optionalAuthor);
        given(genreRepository.findById("genreid")).willReturn(optionalGenre);
        given(bookRepository.findById("testId")).willReturn(optionalBook);

        mvc.perform(post("/books/save")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId")
                    .param("name", strName)
                    .param("authorId", "authorid")
                    .param("genreId", "genreid"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/books"));
    }
}
