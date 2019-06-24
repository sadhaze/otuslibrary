package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import otus.library.domain.Genre;
import otus.library.repository.GenreRepository;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @PutMapping("/{id}/name/{name}")
    public Genre editGenre(@PathVariable("id") String id, @PathVariable("name") String name) {
        Genre genre = genreRepository.findById(id).orElseThrow(NotFoundException::new);
        genre.setName(name);
        return genreRepository.save(genre);
    }

    @PostMapping("/name/{name}")
    public Genre createGenre(@PathVariable("name") String name) {
        return genreRepository.save(new Genre(name));
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") String id) {
        genreRepository.deleteById(id);
    }
}
