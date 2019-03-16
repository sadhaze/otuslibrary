package otus.library.domain;

public class Genre {
    private final Long id;
    private final String name;

    public Genre(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
