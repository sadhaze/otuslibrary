package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Тест DAO книг")
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    @DisplayName("Получение количества книг")
    void bookDaoCountTest(){
        Assertions.assertEquals(4L, bookRepository.count());
    }

    @Test
    @DisplayName("Получение списка книг")
    void bookDaoGetAllTest(){
        Assertions.assertEquals("TestRuslan i Lyudmila", bookRepository.findAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение книги")
    void bookDaoInsertAndGetByIdTest(){
        Long id = 10L;
        authorRepository.save(new Author(id, "TestFName", "TestLName"));
        genreRepository.save(new Genre(id, "TestGenre"));
        bookRepository.save(
                new Book(id, "booktest",
                        authorRepository.getOne(id),
                        genreRepository.getOne(id)
                )
        );

        Book book = bookRepository.getOne(id);

        Assertions.assertEquals("booktest", book.getName());
        Assertions.assertEquals("TestFName", book.getAuthor().getFname());
        Assertions.assertEquals("TestLName", book.getAuthor().getLname());
        Assertions.assertEquals("TestGenre", book.getGenre().getName());
    }
}
