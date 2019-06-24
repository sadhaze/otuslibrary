package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import otus.library.domain.Author;
import otus.library.repository.AuthorRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/api/authors")
    public Flux<Author> listPage() {
        return authorRepository.findAll();
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(AuthorRepository repository) {
        System.out.println("Here we are now, entertain us!");
        return route()
                .GET("/flux/authors", request -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), Author.class))
                //.GET("/flux/authors", request -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), Author.class))
                //.GET("/flux/authors", accept(), request -> repository.findAll().flatMap(author -> ok().contentType(APPLICATION_JSON).body(fromObject(author))))
                /*.GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(request.pathVariable("id"))
                                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                )*/
                .build();
    }

    /*@GetMapping("/authors/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Author authors = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("authors", authors);
        return "authors/edit";
    }

    @PostMapping("/authors/create")
    public String createAuthor(@RequestParam("lname") String lname, @RequestParam("fname") String fname, Model model) {
        authorRepository.save(new Author(fname, lname));
        model.addAttribute("backref", "/authors");
        return "save";
    }

    @PostMapping("/authors/delete")
    public String deleteAuthor(@RequestParam("id") String id, Model model) {
        authorRepository.deleteById(id);
        model.addAttribute("backref", "/authors");
        return "save";
    }

    @GetMapping("/authors/new")
    public String newPage() {
        return "authors/new";
    }

    @PostMapping("/authors/save")
    public String saveAuthor(@RequestParam("id") String id, @RequestParam("lname") String lname, @RequestParam("fname") String fname, Model model) {
        Optional<Author> author = authorRepository.findById(id);
        author.get().setFname(fname);
        author.get().setLname(lname);
        authorRepository.save(author.get());
        model.addAttribute("backref", "/authors");
        return "save";
    }*/
}