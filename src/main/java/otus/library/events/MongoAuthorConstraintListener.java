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
import otus.library.domain.Author;
import otus.library.domain.Book;

@Component
@RequiredArgsConstructor
public class MongoAuthorConstraintListener extends AbstractMongoEventListener<Author> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        ObjectId id = new ObjectId(source.get("_id").toString());
        Query query = new Query(Criteria.where("author.$id").is(id));
        if(mongoOperations.count(query, Book.class)!=0L){
            Update update = new Update();
            update.set("author.$id", "AUTHORWASDELETED");
            mongoOperations.findAndModify(query, update, Book.class);
            System.out.println("WARNING!!! You delete Author who have books. " +
                    "Author ID in collection.book chenged on AUTHORWASDELETED. " +
                    "It must be correct if that need.");
        }
    }
}
