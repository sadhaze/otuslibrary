package otus.library.domain;

public class Book {
    //CREATE TABLE BOOK(ID INT PRIMARY KEY, NAME VARCHAR(255), AUTHOR VARCHAR(255), GENRE VARCHAR(255));

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
