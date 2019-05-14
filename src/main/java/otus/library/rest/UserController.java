package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.library.domain.User;
import otus.library.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOperations;

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

    @GetMapping("/users/create")
    public String createUser(@RequestParam("id") String id) {
        userRepository.save(new User(id));
        return "users/save";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam("id") String id) {
        userRepository.deleteById(id);
        return "users/save";
    }

    @GetMapping("/users/new")
    public String newPage() {
        return "users/new";
    }
}
