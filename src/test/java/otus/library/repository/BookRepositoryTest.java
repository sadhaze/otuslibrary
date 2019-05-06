package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;
import java.util.Iterator;

@DataMongoTest
@ActiveProfiles("test")
@DisplayName("Тест DAO книг")
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @BeforeEach
    void dbWiper(){
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
        String id1 = "0";
        String id2 = "1";
        String id3 = "2";
        authorRepository.save(new Author("fn1", "ln1"));
        authorRepository.save(new Author("fn2", "ln2"));
        authorRepository.save(new Author("fn3", "ln3"));
        genreRepository.save(new Genre("g1"));
        genreRepository.save(new Genre("g2"));
        genreRepository.save(new Genre("g3"));
        bookRepository.save(new Book("b1", authorRepository.findById(id1).get(), genreRepository.findById(id1).get()));
        bookRepository.save(new Book("b2", authorRepository.findById(id2).get(), genreRepository.findById(id2).get()));
        bookRepository.save(new Book("b3", authorRepository.findById(id3).get(), genreRepository.findById(id3).get()));
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение количества книг")
    void bookDaoCountTest(){
        Assertions.assertEquals(3L, bookRepository.count());
    }

    @Test
    @DirtiesContext
    @DisplayName("Получение списка книг")
    void bookDaoGetAllTest(){
        Iterator<Book> iterator = bookRepository.findAll().iterator();
        Assertions.assertEquals("b1", iterator.next().getName());
        Assertions.assertEquals("b2", iterator.next().getName());
        Assertions.assertEquals("b3", iterator.next().getName());
    }

    @Test
    @DirtiesContext
    @DisplayName("Вставка и получение книги")
    void bookDaoInsertAndGetByIdTest(){
        Book book = bookRepository.findById("0").get();
        Assertions.assertEquals("b1", book.getName());
        Assertions.assertEquals("fn1", book.getAuthor().getFname());
        Assertions.assertEquals("ln1", book.getAuthor().getLname());
        Assertions.assertEquals("g1", book.getGenre().getName());
    }
}
