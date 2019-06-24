package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.*;
import java.util.Iterator;

@DataMongoTest
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
        authorRepository.save(new Author("fn1", "ln1"));
        authorRepository.save(new Author("fn2", "ln2"));
        authorRepository.save(new Author("fn3", "ln3"));
        genreRepository.save(new Genre("g1"));
        genreRepository.save(new Genre("g2"));
        genreRepository.save(new Genre("g3"));
        /*Iterator<Author> iteratorAuthor = authorRepository.findAll().iterator();
        Iterator<Genre> iteratorGenre = genreRepository.findAll().iterator();
        bookRepository.save(new Book("b1", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
        bookRepository.save(new Book("b2", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
        bookRepository.save(new Book("b3", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
        */userRepository.save(new User("u1"));
        userRepository.save(new User("u2"));
        userRepository.save(new User("u3"));
        Iterator<Book> iteratorBook = bookRepository.findAll().iterator();
        commentRepository.save(new Comment(bookRepository.findById(iteratorBook.next().getId()).get(), userRepository.findById("u1").get(), "c1"));
        commentRepository.save(new Comment(bookRepository.findById(iteratorBook.next().getId()).get(), userRepository.findById("u2").get(), "c2"));
        commentRepository.save(new Comment(bookRepository.findById(iteratorBook.next().getId()).get(), userRepository.findById("u3").get(), "c3"));

    }

    @Test
    @DirtiesContext
    @DisplayName("Получение количества комментариев")
    void commentJpaCountTest(){
        Assertions.assertEquals(3L, commentRepository.count());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение списка комментариев")
    void commentJpaGetAllTest(){
        Iterator<Comment> iterator = commentRepository.findAll().iterator();
        Assertions.assertEquals("c1", iterator.next().getComment());
        Assertions.assertEquals("c2", iterator.next().getComment());
        Assertions.assertEquals("c3", iterator.next().getComment());
    }

    @Test
    @DirtiesContext
    @DisplayName("Вставка и получение комментария")
    void commentJpaInsertAndGetByIdTest(){
        Iterator<Comment> iterator = commentRepository.findAll().iterator();
        Assertions.assertEquals("c1", commentRepository.findById(iterator.next().getId()).get().getComment());
    }
}
