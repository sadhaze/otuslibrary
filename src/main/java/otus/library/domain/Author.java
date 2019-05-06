package otus.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author {
    @Id
    private String id;

    private String fname;
    private String lname;

    public Author(){}

    public Author(String fname, String lname){
        this.setFname(fname);
        this.setLname(lname);
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
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
