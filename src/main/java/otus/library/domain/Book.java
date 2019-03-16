package otus.library.domain;

public class Book {
    private final Long id;
    private final String name;
    private final Author author;
    private final Genre genre;

    public Book(Long id, String name, Author author, Genre genre){
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Author getAuthor(){
        return this.author;
    }

    public Genre getGenre(){
        return this.genre;
    }
}
