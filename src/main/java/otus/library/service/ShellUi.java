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
        return "Author by number " + authorDao.getById(id).getId() + " is " + authorDao.getById(id).getFname() + " " + authorDao.getById(id).getLname();
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
        return "Book by number " + bookDao.getById(id).getId() + " is " + bookDao.getById(id).getName()
                + ". Genre: " + genreDao.getById(bookDao.getById(id).getGenre()).getName()
                + ". Author: " + authorDao.getById(bookDao.getById(id).getAuthor()).getFname() + " "
                + authorDao.getById(bookDao.getById(id).getAuthor()).getLname();
    }

    @ShellMethod(value = "Show all book")
    public void getBookAll(){
        List<Book> book = bookDao.getAll();
        book.forEach(item->System.out.println(item.getId() + ": " + item.getName()
                + ". Genre: " + genreDao.getById(bookDao.getById(item.getGenre()).getGenre()).getName()
                + ". Author: " + authorDao.getById(bookDao.getById(item.getAuthor()).getAuthor()).getFname() + " "
                + authorDao.getById(bookDao.getById(item.getAuthor()).getAuthor()).getLname()
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
        return "Genre by number " + genreDao.getById(id).getId() + " is " + genreDao.getById(id).getName();
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
