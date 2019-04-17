package otus.library.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.library.repository.AuthorRepository;
import otus.library.repository.BookRepository;
import otus.library.repository.GenreRepository;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

import java.util.List;

@ShellComponent
public class ShellUi {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    GenreRepository genreRepository;

    private ShellUi(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @ShellMethod(value = "Show author by ID")
    public String getAuthor(Long id){
        Author author = authorRepository.getById(id);
        return "Author by number " + author.getId() + " is " + author.getFname() + " " + author.getLname();
    }

    @ShellMethod(value = "Show all author")
    public void getAuthorAll(){
        List<Author> author = authorRepository.getAll();
        author.forEach(item->System.out.println(item.getId() + ": " + item.getLname() + " " + item.getFname() + "\n"));
    }

    @ShellMethod(value = "Show author count")
    public String getAuthorCount(){
        return "Author count is " + authorRepository.count();
    }

    @ShellMethod(value = "Insert new author")
    public void insertAuthor(Long id, String fname, String lname){
        authorRepository.insert(new Author(id, fname, lname));
    }

    @ShellMethod(value = "Show book by ID")
    public String getBook(Long id){
        Book book = bookRepository.getById(id);
        return "Book by number " + book.getId() + " is " + book.getName()
                + ". Genre: " + book.getGenre().getName()
                + ". Author: " + book.getAuthor().getFname() + " "
                + book.getAuthor().getLname();
    }

    @ShellMethod(value = "Show all book")
    public void getBookAll(){
        List<Book> book = bookRepository.getAll();
        book.forEach(item->System.out.println(item.getId() + ": " + item.getName()
                + ". Genre: " + item.getGenre().getName()
                + ". Author: " + item.getAuthor().getFname() + " "
                + item.getAuthor().getLname()
                + "\n"));
    }

    @ShellMethod(value = "Show book count")
    public String getBookCount(){
        return "Book count is " + bookRepository.count();
    }

    @ShellMethod(value = "Insert new book")
    public void insertBook(Long id, String name, Long author, Long genre){
        bookRepository.insert(new Book(id, name, authorRepository.getById(author), genreRepository.getById(genre)));
    }

    @ShellMethod(value = "Show genre by ID")
    public String getGenre(Long id){
        Genre genre = genreRepository.getById(id);
        return "Genre by number " + genre.getId() + " is " + genre.getName();
    }

    @ShellMethod(value = "Show all genre")
    public void getGenreAll(){
        List<Genre> genre = genreRepository.getAll();
        genre.forEach(item->System.out.println(item.getId() + ": " + item.getName() + "\n"));
    }

    @ShellMethod(value = "Show genre count")
    public String getGenreCount(){
        return "Genre count is " + genreRepository.count();
    }

    @ShellMethod(value = "Insert new genre")
    public void insertGenre(Long id, String name){
        genreRepository.insert(new Genre(id, name));
    }
}
