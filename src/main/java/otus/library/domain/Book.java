package otus.library.domain;

public class Book {
    private final Integer id;
    private final String name;
    private final Integer author;
    private final Integer genre;

    public Book(Integer id, String name, Integer author, Integer genre){
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Integer getAuthor(){
        return this.author;
    }

    public Integer getGenre(){
        return this.genre;
    }
}
