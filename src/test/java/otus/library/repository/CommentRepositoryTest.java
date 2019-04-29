package otus.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import otus.library.domain.*;

@DataJpaTest
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

    @Test
    @DisplayName("Получение количества комментариев")
    void commentJpaCountTest(){
        Assertions.assertEquals(1L, commentRepository.count());
    }

    @Test
    @DisplayName("Получение списка комментариев")
    void commentJpaGetAllTest(){
        Assertions.assertEquals("TestThis book is bullshit", commentRepository.findAll().get(0).getComment());
    }

    @Test
    @DisplayName("Вставка и получение комментария")
    void commentJpaInsertAndGetByIdTest(){
        Long id = 10L;
        String user = "TestUser";

        authorRepository.save(new Author(id, "TestFName", "TestLName"));
        genreRepository.save(new Genre(id, "TestGenre"));
        bookRepository.save(
                new Book(id, "booktest",
                        authorRepository.getOne(id),
                        genreRepository.getOne(id)
                )
        );
        userRepository.save(new User(user));
        commentRepository.save(new Comment(id, bookRepository.getOne(id), userRepository.getOne(user), "TestComment"));

        Assertions.assertEquals("TestComment", commentRepository.getOne(id).getComment());
    }
}
