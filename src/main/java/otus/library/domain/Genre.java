package otus.library.domain;

public class Genre {
    //CREATE TABLE GENRE(ID INT PRIMARY KEY, NAME VARCHAR(255));

    private final Integer id;
    private final String name;

    public Genre(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
