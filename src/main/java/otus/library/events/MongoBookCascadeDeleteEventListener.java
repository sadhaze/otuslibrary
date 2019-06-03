package otus.library.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import otus.library.domain.Book;
import otus.library.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        commentRepository.deleteAllByBookId(source.get("_id").toString());
    }
}
