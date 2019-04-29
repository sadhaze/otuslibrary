package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.*;

import java.util.Iterator;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")
@DisplayName("Тест JPA комментариев")
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void dbWiper(){
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
        userRepository.deleteAll();
        commentRepository.deleteAll();
        Long id1 = 0L;
        Long id2 = 1L;
        Long id3 = 2L;
        authorRepository.save(new Author(id1, "fn1", "ln1"));
        authorRepository.save(new Author(id2, "fn2", "ln2"));
        authorRepository.save(new Author(id3, "fn3", "ln3"));
        genreRepository.save(new Genre(id1, "g1"));
        genreRepository.save(new Genre(id2, "g2"));
        genreRepository.save(new Genre(id3, "g3"));
        bookRepository.save(new Book(id1, "b1", authorRepository.findById(id1).get(), genreRepository.findById(id1).get()));
        bookRepository.save(new Book(id2, "b2", authorRepository.findById(id2).get(), genreRepository.findById(id2).get()));
        bookRepository.save(new Book(id3, "b3", authorRepository.findById(id3).get(), genreRepository.findById(id3).get()));
        userRepository.save(new User("u1"));
        userRepository.save(new User("u2"));
        userRepository.save(new User("u3"));
        commentRepository.save(new Comment(id1, bookRepository.findById(id1).get(), userRepository.findById("u1").get(), "c1"));
        commentRepository.save(new Comment(id2, bookRepository.findById(id2).get(), userRepository.findById("u2").get(), "c2"));
        commentRepository.save(new Comment(id3, bookRepository.findById(id3).get(), userRepository.findById("u3").get(), "c3"));

    }

    @Test
    @DisplayName("Получение количества комментариев")
    void commentJpaCountTest(){
        Assertions.assertEquals(3L, commentRepository.count());
    }

    @Test
    @DisplayName("Получение списка комментариев")
    void commentJpaGetAllTest(){
        Iterator<Comment> iterator = commentRepository.findAll().iterator();
        Assertions.assertEquals("c1", iterator.next().getComment());
        Assertions.assertEquals("c2", iterator.next().getComment());
        Assertions.assertEquals("c3", iterator.next().getComment());
    }

    @Test
    @DisplayName("Вставка и получение комментария")
    void commentJpaInsertAndGetByIdTest(){
        Assertions.assertEquals("c1", commentRepository.findById(0L).get().getComment());
    }
}
