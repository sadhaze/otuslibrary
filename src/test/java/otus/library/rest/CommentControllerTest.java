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
import otus.library.domain.*;
import otus.library.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@ActiveProfiles("test")
@DisplayName("Тест контроллера комментариев")
public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CommentRepository commentRepository;

    private final String strComment = "testComment";
    private final Author author = new Author("testFname", "testLname");
    private final Genre genre = new Genre("testGenre");
    private final Book book = new Book("testBook", author, genre);
    private final User user = new User("testUser");
    private final Comment comment = new Comment(book, user, strComment);


    @Test
    @DisplayName("Проверка страницы /comments")
    public void commentListPageTest() throws Exception {
        List<Comment> allComments = Arrays.asList(comment);

        given(commentRepository.findAll()).willReturn(allComments);

        mvc.perform(get("/comments").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("comments/list"))
                .andExpect(model().attribute("comments", allComments));
    }

    @Test
    @DisplayName("Проверка страницы /comments/edit")
    public void commentEditTest() throws Exception {
        List<Book> allBooks = Arrays.asList(book);
        List<User> allUser = Arrays.asList(user);
        given(bookRepository.findAll()).willReturn(allBooks);
        given(userRepository.findAll()).willReturn(allUser);
        Optional<Comment> optionalComment = Optional.ofNullable(comment);

        given(commentRepository.findById("testId")).willReturn(optionalComment);

        mvc.perform(get("/comments/edit")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("comments/edit"))
                .andExpect(model().attribute("comments", optionalComment.get()));
    }

    @Test
    @DisplayName("Проверка страницы /comments/create")
    public void commentCreateTest() throws Exception {
        Optional<Book> optionalBook = Optional.ofNullable(book);
        Optional<User> optionalUser = Optional.ofNullable(user);
        given(bookRepository.findById("bookid")).willReturn(optionalBook);
        given(userRepository.findById("userid")).willReturn(optionalUser);
        given(commentRepository.save(comment)).willReturn(comment);

        mvc.perform(post("/comments/create")
                .contentType(MediaType.TEXT_HTML)
                .param("bookId", "bookid")
                .param("userId", "userid")
                .param("comment", strComment))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/comments"));
    }

    @Test
    @DisplayName("Проверка страницы /comments/delete")
    public void commentDeleteTest() throws Exception {
        mvc.perform(post("/comments/delete")
                .contentType(MediaType.TEXT_HTML)
                .param("id", "testId"))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/comments"));
    }

    @Test
    @DisplayName("Проверка страницы /comments/new")
    public void commentNewTest() throws Exception {
        mvc.perform(get("/comments/new").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("comments/new"));
    }

    @Test
    @DisplayName("Проверка страницы /comments/save")
    public void commentSaveTest() throws Exception {
        Optional<Book> optionalBook = Optional.ofNullable(book);
        Optional<User> optionalUser = Optional.ofNullable(user);
        given(bookRepository.findById("bookid")).willReturn(optionalBook);
        given(userRepository.findById("userid")).willReturn(optionalUser);
        given(commentRepository.save(comment)).willReturn(comment);

        mvc.perform(post("/comments/save")
                    .contentType(MediaType.TEXT_HTML)
                    .param("id", "testId")
                    .param("bookId", "bookid")
                    .param("userId", "userid")
                    .param("comment", strComment))
                .andExpect(status().isOk())
                .andExpect(view().name("save"))
                .andExpect(model().attribute("backref", "/comments"));
    }
}
