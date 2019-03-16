package otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import otus.library.domain.Author;
import otus.library.domain.Book;
import otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;

    private static class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("book.name");
            Author author = new Author(
                    resultSet.getLong("author.id"),
                    resultSet.getString("author.fname"),
                    resultSet.getString("author.lname")
            );
            Genre genre = new Genre(
                    resultSet.getLong("genre.id"),
                    resultSet.getString("genre.name")
            );
            return new Book(id, name, author, genre);
        }
    }

    public BookDaoJdbc(JdbcOperations jdbc){
        this.jdbc = jdbc;
    }

    public Long count(){
        return jdbc.queryForObject("select count(*) from book", Long.class);
    }

    public void insert(Book book){
        jdbc.update("insert into book (id, `name`, `author_id`, `genre_id`) values (?, ?, ?, ?)", book.getId(), book.getName(), book.getAuthor().getId(), book.getGenre().getId());
    }

    public Book getById(Long id){
        return jdbc.queryForObject(
                "select * " +
                        "from book b " +
                        "join author a on b.author_id = a.id " +
                        "join genre g on b.genre_id = g.id " +
                        "where b.id = ? ",
                new Object[] {id}, new BookDaoJdbc.BookMapper());
    }

    public List<Book> getAll(){
        return jdbc.query(
                "select * " +
                        "from book b " +
                        "join author a on b.author_id = a.id " +
                        "join genre g on b.genre_id = g.id ",
                new BookDaoJdbc.BookMapper());
    }
}
