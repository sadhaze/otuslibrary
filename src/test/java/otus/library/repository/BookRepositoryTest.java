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
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

import java.util.Iterator;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
    }

    @Test
    @DisplayName("Получение количества книг")
    void bookDaoCountTest(){
        Assertions.assertEquals(3L, bookRepository.count());
    }

    @Test
    @DisplayName("Получение списка книг")
    void bookDaoGetAllTest(){
        Iterator<Book> iterator = bookRepository.findAll().iterator();
        Assertions.assertEquals("b1", iterator.next().getName());
        Assertions.assertEquals("b2", iterator.next().getName());
        Assertions.assertEquals("b3", iterator.next().getName());
    }

    @Test
    @DisplayName("Вставка и получение книги")
    void bookDaoInsertAndGetByIdTest(){
        Book book = bookRepository.findById(0L).get();
        Assertions.assertEquals("b1", book.getName());
        Assertions.assertEquals("fn1", book.getAuthor().getFname());
        Assertions.assertEquals("ln1", book.getAuthor().getLname());
        Assertions.assertEquals("g1", book.getGenre().getName());
    }
}
