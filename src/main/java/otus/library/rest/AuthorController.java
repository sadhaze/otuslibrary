package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.library.domain.Author;
import otus.library.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public String listPage(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    @GetMapping("/authors/edit")
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
    }
}