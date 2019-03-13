package otus.library.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.library.dao.AuthorDao;
import otus.library.dao.BookDao;
import otus.library.dao.GenreDao;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

import java.util.List;

@ShellComponent
public class ShellUi {
    AuthorDao authorDao;
    BookDao bookDao;
    GenreDao genreDao;

    private ShellUi(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao){
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Show author by ID")
    public String getAuthor(Integer id){
        Author author = authorDao.getById(id);
        return "Author by number " + author.getId() + " is " + author.getFname() + " " + author.getLname();
    }

    @ShellMethod(value = "Show all author")
    public void getAuthorAll(){
        List<Author> author = authorDao.getAll();
        author.forEach(item->System.out.println(item.getId() + ": " + item.getLname() + " " + item.getFname() + "\n"));
    }

    @ShellMethod(value = "Show author count")
    public String getAuthorCount(){
        return "Author count is " + authorDao.count();
    }

    @ShellMethod(value = "Insert new author")
    public void insertAuthor(Integer id, String fname, String lname){
        authorDao.insert(new Author(id, fname, lname));
    }

    @ShellMethod(value = "Show book by ID")
    public String getBook(Integer id){
        Book book = bookDao.getById(id);
        return "Book by number " + book.getId() + " is " + book.getName()
                + ". Genre: " + book.getGenreName()
                + ". Author: " + book.getAuthorFname() + " "
                + book.getAuthorLname();
    }

    @ShellMethod(value = "Show all book")
    public void getBookAll(){
        List<Book> book = bookDao.getAll();
        book.forEach(item->System.out.println(item.getId() + ": " + item.getName()
                + ". Genre: " + item.getGenreName()
                + ". Author: " + item.getAuthorFname() + " "
                + item.getAuthorLname()
                + "\n"));
    }

    @ShellMethod(value = "Show book count")
    public String getBookCount(){
        return "Book count is " + bookDao.count();
    }

    @ShellMethod(value = "Insert new book")
    public void insertBook(Integer id, String name, Integer author, Integer genre){
        bookDao.insert(new Book(id, name, author, genre));
    }

    @ShellMethod(value = "Show genre by ID")
    public String getGenre(Integer id){
        Genre genre = genreDao.getById(id);
        return "Genre by number " + genre.getId() + " is " + genre.getName();
    }

    @ShellMethod(value = "Show all genre")
    public void getGenreAll(){
        List<Genre> genre = genreDao.getAll();
        genre.forEach(item->System.out.println(item.getId() + ": " + item.getName() + "\n"));
    }

    @ShellMethod(value = "Show genre count")
    public String getGenreCount(){
        return "Genre count is " + genreDao.count();
    }

    @ShellMethod(value = "Insert new genre")
    public void insertGenre(Integer id, String name){
        genreDao.insert(new Genre(id, name));
    }
}
