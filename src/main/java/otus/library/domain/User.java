package otus.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;

    public User(){}

    public User(String user){
        this.setId(user);
    }

    public String getId(){
        return this.id;
    }

    public void setId(String user){
        this.id = user;
    }
}
