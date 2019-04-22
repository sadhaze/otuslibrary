package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.library.domain.*;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
@DisplayName("Тест JPA комментариев")
public class CommentRepositoryTest {
    @Autowired
   CommentRepository commentRepository;

    @Test
    @DisplayName("Получение количества комментариев")
    void commentJpaCountTest(){
        Assertions.assertEquals("1", commentRepository.count().toString());
    }

    @Test
    @DisplayName("Получение списка комментариев")
    void commentJpaGetAllTest(){
        Assertions.assertEquals("This book is bullshit", commentRepository.getAll().get(0).getComment());
    }

    @Test
    @DisplayName("Вставка и получение комментария")
    void commentJpaInsertAndGetByIdTest(){
        Long id = new Long(10);
        Book book = new Book(id, "booktest",
                        new Author(id, "TestFName", "TestLName"),
                        new Genre(id, "TestGenre")
                );
        User user = new User("TestUser");
        Comment comment = new Comment(id, book, user, "TestComment");
        commentRepository.insert(comment);
        Assertions.assertEquals("TestComment", commentRepository.getById(id).getComment());
    }
}
