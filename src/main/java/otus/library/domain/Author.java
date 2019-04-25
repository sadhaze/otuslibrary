package otus.library.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Proxy(lazy = false)
public class Author {
    @Id
    private Long id;

    private String fname;
    private String lname;

    public Author(){}

    public Author(Long id, String fname, String lname){
        this.setId(id);
        this.setFname(fname);
        this.setLname(lname);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFname(){
        return this.fname;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public String getLname(){
        return this.lname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }
}
