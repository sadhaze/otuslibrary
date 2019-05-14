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
import otus.library.domain.Genre;
import otus.library.repository.GenreRepository;

import java.util.List;

@Controller
public class GenreController {
    private final GenreRepository genreRepository;

    @Autowired
    private MongoOperations mongoOperations;

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

    @GetMapping("/genres/create")
    public String createGenre(@RequestParam("name") String name) {
        genreRepository.save(new Genre(name));
        return "genres/save";
    }

    @GetMapping("/genres/delete")
    public String deleteGenre(@RequestParam("id") String id) {
        genreRepository.deleteById(id);
        return "genres/save";
    }

    @GetMapping("/genres/new")
    public String newPage() {
        return "genres/new";
    }

    @GetMapping("/genres/save")
    public void saveGenre(@RequestParam("id") String id, @RequestParam("name") String name) {
        Query query = new Query(Criteria.where("id").is(id));
        if(mongoOperations.exists(query, Genre.class)){
            Update update = new Update();
            update.set("name", name);
            mongoOperations.findAndModify(query, update, Genre.class);
        }
    }
}
