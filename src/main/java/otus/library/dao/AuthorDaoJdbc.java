package otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import otus.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final JdbcOperations jdbc;

    private static class AuthorMapper implements RowMapper<Author>{
        public Author mapRow(ResultSet resultSet, int i) throws SQLException{
            Integer id = resultSet.getInt("id");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            return new Author(id, fname, lname);
        }
    }

    public AuthorDaoJdbc(JdbcOperations jdbc){
        this.jdbc = jdbc;
    }

    public Integer count(){
        return jdbc.queryForObject("select count(*) from author", Integer.class);
    }

    public void insert(Author author){
        jdbc.update("insert into author (id, `fname`, `lname`) values (?, ?, ?)", author.getId(), author.getFname(), author.getLname());
    }

    public Author getById(Integer id){
        return jdbc.queryForObject("select * from author where id = ?", new Object[] {id}, new AuthorMapper());
    }

    public List<Author> getAll(){
        return jdbc.query("select * from author", new AuthorMapper());
    }
}
