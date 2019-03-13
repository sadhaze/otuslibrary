package otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import otus.library.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;

    private static class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("book.name");
            String authorFname = resultSet.getString("fname");
            String authorLname = resultSet.getString("lname");
            String genreName = resultSet.getString("genrename");
            return new Book(id, name, authorFname, authorLname, genreName);
        }
    }

    public BookDaoJdbc(JdbcOperations jdbc){
        this.jdbc = jdbc;
    }

    public Integer count(){
        return jdbc.queryForObject("select count(*) from book", Integer.class);
    }

    public void insert(Book book){
        jdbc.update("insert into book (id, `name`, `author`, `genre`) values (?, ?, ?, ?)", book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

    public Book getById(Integer id){
        return jdbc.queryForObject(
                "select " +
                        "b.id, " +
                        "b.name, " +
                        "a.fname, " +
                        "a.lname, " +
                        "g.name as genrename " +
                        "from book b " +
                        "join author a on b.author = a.id " +
                        "join genre g on b.genre = g.id " +
                        "where b.id = ?",
                new Object[] {id}, new BookDaoJdbc.BookMapper());
    }

    public List<Book> getAll(){
        return jdbc.query(
                "select " +
                        "b.id, " +
                        "b.name, " +
                        "a.fname, " +
                        "a.lname, " +
                        "g.name as genrename " +
                        "from book b " +
                        "join author a on b.author = a.id " +
                        "join genre g on b.genre = g.id ",
                new BookDaoJdbc.BookMapper());
    }
}
