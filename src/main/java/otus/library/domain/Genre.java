package otus.library.domain;

public class Genre {
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
