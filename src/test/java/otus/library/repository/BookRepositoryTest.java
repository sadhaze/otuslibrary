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

 /*   @BeforeEach
    void dbWiper(){
        authorRepository.save(new Author("fn1", "ln1"));
        authorRepository.save(new Author("fn2", "ln2"));
        authorRepository.save(new Author("fn3", "ln3"));
        genreRepository.save(new Genre("g1"));
        genreRepository.save(new Genre("g2"));
        genreRepository.save(new Genre("g3"));
        Iterator<Author> iteratorAuthor = authorRepository.findAll().iterator();
        Iterator<Genre> iteratorGenre = genreRepository.findAll().iterator();
        bookRepository.save(new Book("b1", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
        bookRepository.save(new Book("b2", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
        bookRepository.save(new Book("b3", authorRepository.findById(iteratorAuthor.next().getId()).get(), genreRepository.findById(iteratorGenre.next().getId()).get()));
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
        Iterator<Book> iterator = bookRepository.findAll().iterator();
        Book book = bookRepository.findById(iterator.next().getId()).get();
        Assertions.assertEquals("b1", book.getName());
        Assertions.assertEquals("fn1", book.getAuthor().getFname());
        Assertions.assertEquals("ln1", book.getAuthor().getLname());
        Assertions.assertEquals("g1", book.getGenre().getName());
    }*/
}
