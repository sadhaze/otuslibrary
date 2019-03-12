package otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final JdbcOperations jdbc;

    private static class GenreMapper implements RowMapper<Genre> {
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }

    public GenreDaoJdbc(JdbcOperations jdbc){
        this.jdbc = jdbc;
    }

    public Integer count(){
        return jdbc.queryForObject("select count(*) from genre", Integer.class);
    }

    public void insert(Genre genre){
        jdbc.update("insert into genre (id, `name`) values (?, ?)", genre.getId(), genre.getName());
    }

    public Genre getById(Integer id){
        return jdbc.queryForObject("select * from genre where id = ?", new Object[] {id}, new GenreDaoJdbc.GenreMapper());
    }

    public List<Genre> getAll(){
        return jdbc.query("select * from genre", new GenreDaoJdbc.GenreMapper());
    }
}
