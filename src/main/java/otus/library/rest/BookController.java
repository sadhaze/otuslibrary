package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;
import otus.library.repository.AuthorRepository;
import otus.library.repository.BookRepository;
import otus.library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/books")
    public String listPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Book books = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/edit";
    }

    @PostMapping("/books/create")
    public String createBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId, @RequestParam("genreId") String genreId, Model model) {
        bookRepository.save(new Book(name,
                authorRepository.findById(authorId).orElseThrow(NotFoundException::new),
                genreRepository.findById(genreId).orElseThrow(NotFoundException::new)));
        model.addAttribute("backref", "/books");
        return "save";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam("id") String id, Model model) {
        bookRepository.deleteById(id);
        model.addAttribute("backref", "/books");
        return "save";
    }

    @GetMapping("/books/new")
    public String newPage(Model model) {
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/new";
    }

    @PostMapping("/books/save")
    public String saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId, @RequestParam("genreId") String genreId, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        book.get().setName(name);
        book.get().setAuthor(authorRepository.findById(authorId).orElseThrow(NotFoundException::new));
        book.get().setGenre(genreRepository.findById(genreId).orElseThrow(NotFoundException::new));
        bookRepository.save(book.get());
        model.addAttribute("backref", "/books");
        return "save";
    }
}
