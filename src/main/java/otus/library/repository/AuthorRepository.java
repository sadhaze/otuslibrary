package otus.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.library.domain.Author;
import reactor.core.publisher.Flux;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findAll();
}
