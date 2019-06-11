package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import otus.library.domain.User;
import otus.library.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus
    public Object createUser(@RequestParam("id") String id) {
        userRepository.save(new User(id));
        RedirectView redirectView = new RedirectView("/");
        redirectView.setStatusCode(HttpStatus.CREATED);
        return redirectView;
    }

    @DeleteMapping("{userid}")
    public String deleteUser(@PathVariable("userid") String id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }
}
