package otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.library.domain.Book;
import otus.library.domain.Comment;
import otus.library.repository.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public CommentController(CommentRepository commentRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/comments")
    public String listPage(Model model) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "comments/list";
    }

    @GetMapping("/comments/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Comment comments = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("comments", comments);
        return "comments/edit";
    }

    @GetMapping("/comments/create")
    public String createComment(@RequestParam("bookId") String bookId, @RequestParam("userId") String userId, @RequestParam("comment") String comment) {
        commentRepository.save(new Comment(bookRepository.findById(bookId).orElseThrow(NotFoundException::new),
                userRepository.findById(userId).orElseThrow(NotFoundException::new),
                comment));
        return "comments/save";
    }

    @GetMapping("/comments/delete")
    public String deleteComment(@RequestParam("id") String id) {
        commentRepository.deleteById(id);
        return "comments/save";
    }

    @GetMapping("/comments/new")
    public String newPage() {
        return "comments/new";
    }

    @GetMapping("/comments/save")
    public void saveComment(@RequestParam("id") String id,
                            @RequestParam("bookId") String bookId,
                            @RequestParam("userId") String userId,
                            @RequestParam("comment") String comment) {
        Query query = new Query(Criteria.where("id").is(id));
        if(mongoOperations.exists(query, Comment.class)){
            Update update = new Update();
            update.set("book", bookRepository.findById(bookId).orElseThrow(NotFoundException::new));
            update.set("user", userRepository.findById(userId).orElseThrow(NotFoundException::new));
            update.set("comment", comment);
            mongoOperations.findAndModify(query, update, Comment.class);
        }
    }
}
