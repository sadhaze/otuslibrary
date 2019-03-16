package otus.library.domain;

public class Author {
    private final Long id;
    private final String fname;
    private final String lname;

    public Author(Long id, String fname, String lname){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Long getId(){
        return this.id;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }
}
