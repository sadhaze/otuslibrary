package otus.library.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import otus.library.domain.Author;
import otus.library.repository.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Тест контроллера авторов")
public class AuthorControllerTest {
    @Autowired
    @Qualifier("authorsRoutes")
    private RouterFunction route;

    @Autowired
    private AuthorRepository authorRepository;

    private final String strFname = "testFname";
    private final String strLname = "testLname";
    private final Author author = new Author(strFname, strLname);

    @Test
    @DisplayName("Проверка страницы /flux/authors")
    public void authorsRoutesTest() throws Exception {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        client.post()
                .uri("/flux/authors?lname=" + strLname + "&fname=" + strFname)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("{\"fname\":\"" + strFname + "\",\"lname\":\"" + strLname + "\"}");

        client.get()
                .uri("/flux/authors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("[{\"fname\":\"" + strFname + "\",\"lname\":\"" + strLname + "\"}]");

        String testId = authorRepository.findAll().blockFirst().getId();

        client.put()
                .uri("/flux/authors/" + testId + "?fname=" + strLname + "&lname=" + strFname)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("{\"fname\":\"" + strLname + "\",\"lname\":\"" + strFname + "\"}");

        client.delete()
                .uri("/flux/authors/" + testId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("");

        client.get()
                .uri("/flux/authors")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("[]");

    }
}
