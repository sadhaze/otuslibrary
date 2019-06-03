package otus.library.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoAuthorConstraintListener extends AbstractMongoEventListener<Author> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        List<Book> books = bookRepository.findAllByAuthorId(source.get("_id").toString());
        books.forEach(book -> {
            book.setAuthor(null);
            bookRepository.save(book);
        });
    }
}
