package otus.library.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.library.domain.*;
import otus.library.repository.*;

import java.util.List;

@ShellComponent
public class ShellUi {
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

    @ShellMethod(value = "Show comment by ID")
    public String getComment(Long id){
        Comment comment = commentRepository.getById(id);
        return "Comment by number " + comment.getId() + " is " + comment.getComment();
    }

    @ShellMethod(value = "Show all comments")
    public void getCommentAll(){
        List<Comment> comment = commentRepository.getAll();
        comment.forEach(item->System.out.println(item.getId() + ": " + item.getComment() + "\n"));
    }

    @ShellMethod(value = "Show comment count")
    public String getCommentCount(){
        return "Comment count is " + commentRepository.count();
    }

    @ShellMethod(value = "Insert new comment")
    public void insertComment(Long id, Long book, String user, String comment){
        commentRepository.insert(new Comment(id, bookRepository.getById(book), userRepository.getById(user), comment));
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

    @ShellMethod(value = "Show user by ID")
    public String getUser(String id){
        User user = userRepository.getById(id);
        return user != null ? "User by number " + user.getId() + " is exist" : "User by number " + id + " dos't exist";
    }

    @ShellMethod(value = "Show all users")
    public void getUserAll(){
        List<User> user = userRepository.getAll();
        user.forEach(item->System.out.println(item.getId() + "\n"));
    }

    @ShellMethod(value = "Show users count")
    public String getUserCount(){
        return "Users count is " + userRepository.count();
    }

    @ShellMethod(value = "Insert new user")
    public void insertUser(String id){
        userRepository.insert(new User(id));
    }
}
