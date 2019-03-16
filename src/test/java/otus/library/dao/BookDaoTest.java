package otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Тест DAO книг")
public class BookDaoTest {
    @Autowired
    BookDao bookDao;

    @Test
    @DisplayName("Получение количества книг")
    void BookDaoCountTest(){
        Assertions.assertEquals("4", bookDao.count().toString());
    }

    @Test
    @DisplayName("Получение списка книг")
    void BookDaoGetAllTest(){
        Assertions.assertEquals("Ruslan i Lyudmila", bookDao.getAll().get(1).getName());
    }

    @Test
    @DisplayName("Вставка и получение книги")
    void BookDaoInsertAndGetByIdTest(){
        Long bookId = new Long(10);
        Long authorId = new Long(1);
        Long genreId = new Long(1);
        bookDao.insert(
                new Book(bookId, "booktest",
                        new Author(authorId, "", ""),
                        new Genre(genreId, "")
                )
        );
        Book book = bookDao.getById(bookId);
        Assertions.assertEquals("booktest", book.getName());
        Assertions.assertEquals("Bayan", book.getAuthor().getFname());
        Assertions.assertEquals("Shiryanov", book.getAuthor().getLname());
        Assertions.assertEquals("Poem", book.getGenre().getName());
    }
}
