package otus.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comment {
    @Id
    private Long id;

    @OneToOne
    private Book book;

    @OneToOne
    private User user;

    private String comment;

    public Comment(){}

    public Comment(Long id, Book book, User user, String comment){
        this.setId(id);
        this.setBook(book);
        this.setUser(user);
        this.setComment(comment);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Book getBook(){
        return this.book;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

}
