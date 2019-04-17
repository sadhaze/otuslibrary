package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

@DataJpaTest
@Import(BookRepositoryJpa.class)
@DisplayName("Тест DAO книг")
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("Получение количества книг")
    void bookDaoCountTest(){
        Assertions.assertEquals("4", bookRepository.count().toString());
    }

    @Test
    @DisplayName("Получение списка книг")
    void bookDaoGetAllTest(){
        Assertions.assertEquals("Ruslan i Lyudmila", bookRepository.getAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение книги")
    void bookDaoInsertAndGetByIdTest(){
        Long bookId = new Long(10);
        Long authorId = new Long(10);
        Long genreId = new Long(10);
        bookRepository.insert(
                new Book(bookId, "booktest",
                        new Author(authorId, "TestFName", "TestLName"),
                        new Genre(genreId, "TestGenre")
                )
        );
        Book book = bookRepository.getById(bookId);
        Assertions.assertEquals("booktest", book.getName());
        Assertions.assertEquals("TestFName", book.getAuthor().getFname());
        Assertions.assertEquals("TestLName", book.getAuthor().getLname());
        Assertions.assertEquals("TestGenre", book.getGenre().getName());
    }
}
