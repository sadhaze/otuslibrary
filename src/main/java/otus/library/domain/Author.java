package otus.library.domain;

public class Author {
    private final Integer id;
    private final String fname;
    private final String lname;

    public Author(Integer id, String fname, String lname){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Integer getId(){
        return this.id;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }
}
