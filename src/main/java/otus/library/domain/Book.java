package otus.library.domain;

public class Book {
    private final Integer id;
    private final String name;
    private final Integer author;
    private final Integer genre;
    private final String authorFname;
    private final String authorLname;
    private final String genreName;

    public Book(Integer id, String name, Integer author, Integer genre){
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.authorFname = null;
        this.authorLname = null;
        this.genreName = null;
    }

    public Book(Integer id, String name, String authorFname, String authorLname, String genreName){
        this.id = id;
        this.name = name;
        this.author = null;
        this.genre = null;
        this.authorFname = authorFname;
        this.authorLname = authorLname;
        this.genreName = genreName;
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

    public String getAuthorFname(){
        return this.authorFname;
    }

    public String getAuthorLname(){
        return this.authorLname;
    }

    public String getGenreName(){
        return this.genreName;
    }
}
