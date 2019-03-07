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
            String name = resultSet.getString("name");
            Integer author = resultSet.getInt("author");
            Integer genre = resultSet.getInt("genre");
            return new Book(id, name, author, genre);
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
        return jdbc.queryForObject("select * from book where id = ?", new Object[] {id}, new BookDaoJdbc.BookMapper());
    }

    public List<Book> getAll(){
        return jdbc.queryForList("select * from book", Book.class, new BookDaoJdbc.BookMapper());
    }
}
