package otus.library.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import otus.library.domain.Author;
import otus.library.repository.AuthorRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class AuthorController {
    @Bean
    public RouterFunction<ServerResponse> authorsRoutes(AuthorRepository authorRepository) {
        return route()
                .GET("/flux/authors", request -> ok().contentType(APPLICATION_JSON).body(authorRepository.findAll(), Author.class))
                .DELETE("/flux/authors/{id}", request -> authorRepository.deleteById(request.pathVariable("id"))
                        .then(ok().build()))
                .PUT("/flux/authors/{id}", request -> {
                            Author author = new Author();
                            author.setId(request.pathVariable("id"));
                            author.setFname(request.queryParam("fname").get());
                            author.setLname(request.queryParam("lname").get());
                            return ok().contentType(APPLICATION_JSON).body(authorRepository.save(author), Author.class);
                        }
                )
                .POST("/flux/authors",
                        request -> ok().contentType(APPLICATION_JSON).body(authorRepository.save(
                                new Author(request.queryParam("fname").get(), request.queryParam("lname").get())),
                                Author.class
                            )
                )
                .build();
    }
}