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
import otus.library.domain.Book;
import otus.library.repository.AuthorRepository;
import otus.library.repository.BookRepository;
import otus.library.repository.GenreRepository;

import java.util.List;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    private MongoOperations mongoOperations;

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
        model.addAttribute("books", books);
        return "books/edit";
    }

    @GetMapping("/books/create")
    public String createBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId, @RequestParam("genreId") String genreId) {
        bookRepository.save(new Book(name,
                authorRepository.findById(authorId).orElseThrow(NotFoundException::new),
                genreRepository.findById(genreId).orElseThrow(NotFoundException::new)));
        return "books/save";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") String id) {
        bookRepository.deleteById(id);
        return "books/save";
    }

    @GetMapping("/books/new")
    public String newPage() {
        return "books/new";
    }

    @GetMapping("/books/save")
    public void saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId, @RequestParam("genreId") String genreId) {
        Query query = new Query(Criteria.where("id").is(id));
        if(mongoOperations.exists(query, Book.class)){
            Update update = new Update();
            update.set("name", name);
            update.set("author", authorRepository.findById(authorId).orElseThrow(NotFoundException::new));
            update.set("genre", genreRepository.findById(genreId).orElseThrow(NotFoundException::new));
            mongoOperations.findAndModify(query, update, Book.class);
        }
    }
}
