package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.library.domain.Genre;
import otus.library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreController {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public String listPage(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres/list";
    }

    @GetMapping("/genres/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Genre genres = genreRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genres", genres);
        return "genres/edit";
    }

    @PostMapping("/genres/create")
    public String createGenre(@RequestParam("name") String name, Model model) {
        genreRepository.save(new Genre(name));
        model.addAttribute("backref", "/genres");
        return "save";
    }

    @PostMapping("/genres/delete")
    public String deleteGenre(@RequestParam("id") String id, Model model) {
        genreRepository.deleteById(id);
        model.addAttribute("backref", "/genres");
        return "save";
    }

    @GetMapping("/genres/new")
    public String newPage() {
        return "genres/new";
    }

    @PostMapping("/genres/save")
    public String saveGenre(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {
        Optional<Genre> author = genreRepository.findById(id);
        author.get().setName(name);
        genreRepository.save(author.get());
        model.addAttribute("backref", "/genres");
        return "save";
    }
}
