package otus.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Book {
    @Id
    private Long id;

    private String name;

    @OneToOne
    private Author author;

    @OneToOne
    private Genre genre;

    public Book(){}

    public Book(Long id, String name, Author author, Genre genre){
        this.setId(id);
        this.setName(name);
        this.setAuthor(author);
        this.setGenre(genre);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Author getAuthor(){
        return this.author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public Genre getGenre(){
        return this.genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }
}
