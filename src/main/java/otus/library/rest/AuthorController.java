package otus.library.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import otus.library.domain.Author;
import otus.library.repository.AuthorRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class AuthorController {
    @Bean
    public RouterFunction<ServerResponse> authorsRoutes(AuthorRepository authorRepository) {
        return route()
                .GET("/flux/authors", request -> ok().contentType(APPLICATION_JSON).body(authorRepository.findAll(), Author.class))
                .DELETE("/flux/authors/{id}", request -> authorRepository.deleteById(request.pathVariable("id"))
                        .then(ok().build()))
                .PUT("/flux/authors/{id}/lname/{lname}/fname/{fname}", request -> {
                            Author author = new Author();
                            author.setId(request.pathVariable("id"));
                            author.setFname(request.pathVariable("fname"));
                            author.setLname(request.pathVariable("lname"));
                            return ok().contentType(APPLICATION_JSON).body(authorRepository.save(author), Author.class);
                        }
                )
                .POST("/flux/authors/lname/{lname}/fname/{fname}",
                        request -> ok().contentType(APPLICATION_JSON).body(authorRepository.save(
                                new Author(request.pathVariable("fname"), request.pathVariable("lname"))),
                                Author.class
                        )
                )
                .build();
    }
}