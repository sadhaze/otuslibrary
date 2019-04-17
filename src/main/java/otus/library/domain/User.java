package otus.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;

    public User(){}

    public User(String user){
        this.serId(user);
    }

    public String getId(){
        return this.id;
    }

    public void serId(String user){
        this.id = user;
    }
}
