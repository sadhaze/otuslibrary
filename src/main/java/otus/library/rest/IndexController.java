package otus.library.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import otus.library.repository.AuthorRepository;

import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class IndexController {
    @Bean
    public RouterFunction<ServerResponse> indexRoutes(
            @Value("classpath:/static/index.html") final Resource indexHtml,
            @Value("classpath:/static/index.css") final Resource indexCss,
            @Value("classpath:/static/genre.js") final Resource genreJs,
            @Value("classpath:/static/user.js") final Resource userJs,
            @Value("classpath:/static/author.js") final Resource authorJs,
            AuthorRepository authorRepository) {
        return route()
                .GET("/", request -> ok().contentType(TEXT_HTML).syncBody(indexHtml))
                .GET("/index.css", request -> ok().contentType(TEXT_HTML).syncBody(indexCss))
                .GET("/genre.js", request -> ok().contentType(TEXT_HTML).syncBody(genreJs))
                .GET("/user.js", request -> ok().contentType(TEXT_HTML).syncBody(userJs))
                .GET("/author.js", request -> ok().contentType(TEXT_HTML).syncBody(authorJs))
                .build();
    }
}
