package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.library.domain.User;
import otus.library.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String listPage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam("id") String id, Model model) {
        userRepository.save(new User(id));
        model.addAttribute("backref", "/users");
        return "save";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") String id, Model model) {
        userRepository.deleteById(id);
        model.addAttribute("backref", "/users");
        return "save";
    }

    @GetMapping("/users/new")
    public String newPage() {
        return "users/new";
    }
}
