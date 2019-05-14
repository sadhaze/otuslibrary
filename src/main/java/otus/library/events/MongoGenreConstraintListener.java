package otus.library.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import otus.library.domain.Book;
import otus.library.domain.Genre;

@Component
@RequiredArgsConstructor
public class MongoGenreConstraintListener extends AbstractMongoEventListener<Genre> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        ObjectId id = new ObjectId(source.get("_id").toString());
        Query query = new Query(Criteria.where("genre.$id").is(id));
        if(mongoOperations.count(query, Book.class)!=0L){
            Update update = new Update();
            update.set("genre.$id", "GENREWASDELETED");
            mongoOperations.findAndModify(query, update, Book.class);
            System.out.println("WARNING!!! You delete Genre who have books. " +
                    "Genre ID in collection.book chenged on GENREWASDELETED. " +
                    "It must be correct if that need.");
        }
    }
}
