package otus.library.events;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.library.domain.Book;
import otus.library.domain.Genre;
import otus.library.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoGenreConstraintListener extends AbstractMongoEventListener<Genre> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        List<Book> books = bookRepository.findAllByGenreId(source.get("_id").toString());
        books.forEach(book -> {
            book.setGenre(null);
            bookRepository.save(book);
        });
    }
}
