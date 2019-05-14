package otus.library.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.library.domain.*;
import otus.library.repository.*;

import java.util.List;

@ShellComponent
public class ShellUi{
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    CommentRepository commentRepository;
    GenreRepository genreRepository;
    UserRepository userRepository;

    private ShellUi(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            CommentRepository commentRepository,
            GenreRepository genreRepository,
            UserRepository userRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    @ShellMethod(value = "Show author by ID")
    public String getAuthor(String id) throws NoEntityExeption{
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoEntityExeption(id));
        return "Author by number " + author.getId() + " is " + author.getFname() + " " + author.getLname();
    }

    @ShellMethod(value = "Show all author")
    public void getAuthorAll(){
        Iterable<Author> author = authorRepository.findAll();
        author.forEach(item->System.out.println(item.getId() + ": " + item.getLname() + " " + item.getFname() + "\n"));
    }

    @ShellMethod(value = "Show author count")
    public String getAuthorCount(){
        return "Author count is " + authorRepository.count();
    }

    @ShellMethod(value = "Insert new author")
    public void insertAuthor(String fname, String lname){
        authorRepository.save(new Author(fname, lname));
    }

    @ShellMethod(value = "Delete author")
    public void deleteAuthor(String id){
        authorRepository.deleteById(id);
        authorRepository.deleteById(id);
    }

    @ShellMethod(value = "Show book by ID")
    public String getBook(String id) throws NoEntityExeption{
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoEntityExeption(id));
        return "Book by number " + book.getId() + " is " + book.getName()
                + ". Genre: " + book.getGenre().getName()
                + ". Author: " + book.getAuthor().getFname() + " "
                + book.getAuthor().getLname();
    }

    @ShellMethod(value = "Show all book")
    public void getBookAll(){
        Iterable<Book> book = bookRepository.findAll();
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
    public void insertBook(String name, String author, String genre) throws NoEntityExeption {
        bookRepository.save(new Book(name,
                                    authorRepository.findById(author).orElseThrow(() -> new NoEntityExeption(author)),
                                    genreRepository.findById(genre).orElseThrow(() -> new NoEntityExeption(genre))));
    }

    @ShellMethod(value = "Delete book")
    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }

    @ShellMethod(value = "Show comment by ID")
    public String getComment(String id) throws NoEntityExeption {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NoEntityExeption(id));
        return "Comment by number " + comment.getId() + " is " + comment.getComment();
    }

    @ShellMethod(value = "Show all comments")
    public void getCommentAll(){
        Iterable<Comment> comment = commentRepository.findAll();
        comment.forEach(item->System.out.println(item.getId() + ": Воок with name "
                + item.getBook().getName() + " have comment from user "
                + item.getUser().getId() + ": "
                + item.getComment() + "\n"));
    }

    @ShellMethod(value = "Show comment count")
    public String getCommentCount(){
        return "Comment count is " + commentRepository.count();
    }

    @ShellMethod(value = "Insert new comment")
    public void insertComment(String book, String user, String comment) throws NoEntityExeption{
        commentRepository.save(new Comment(bookRepository.findById(book).orElseThrow(() -> new NoEntityExeption(book)),
                                            userRepository.findById(user).orElseThrow(() -> new NoEntityExeption(user)),
                                            comment));
    }

    @ShellMethod(value = "Show genre by ID")
    public String getGenre(String id) throws NoEntityExeption {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NoEntityExeption(id));
        return "Genre by number " + genre.getId() + " is " + genre.getName();
    }

    @ShellMethod(value = "Show all genre")
    public void getGenreAll(){
        Iterable<Genre> genre = genreRepository.findAll();
        genre.forEach(item->System.out.println(item.getId() + ": " + item.getName() + "\n"));
    }

    @ShellMethod(value = "Show genre count")
    public String getGenreCount(){
        return "Genre count is " + genreRepository.count();
    }

    @ShellMethod(value = "Insert new genre")
    public void insertGenre(String name){
        genreRepository.save(new Genre(name));
    }

    @ShellMethod(value = "Show user by ID")
    public String getUser(String id) throws NoEntityExeption {
        User user = userRepository.findById(id).orElseThrow(() -> new NoEntityExeption(id));
        return user != null ? "User by number " + user.getId() + " is exist" : "User by number " + id + " dos't exist";
    }

    @ShellMethod(value = "Show all users")
    public void getUserAll(){
        Iterable<User> user = userRepository.findAll();
        user.forEach(item->System.out.println(item.getId() + "\n"));
    }

    @ShellMethod(value = "Show users count")
    public String getUserCount(){
        return "Users count is " + userRepository.count();
    }

    @ShellMethod(value = "Insert new user")
    public void insertUser(String id){
        userRepository.save(new User(id));
    }
}

class NoEntityExeption extends Exception{
    public NoEntityExeption(String entityId){
        super("Author whit ID " + entityId + " is missing");
    }
}