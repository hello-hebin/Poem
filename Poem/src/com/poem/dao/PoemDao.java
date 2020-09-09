package com.poem.dao;

import com.poem.bean.Poem;
import com.poem.util.C3P0Utils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 处理诗词CRUD的dao
 */
public class PoemDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());

    /**
     * 根据category查询数据
     * @param category
     * @return
     */
    public List<Poem> queryByCategory(String category) {
        String sql = "SELECT * FROM poem WHERE category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Poem.class),category);
    }

    /**
     * 查询所有的诗词列别信息
     * @return
     */
    public List<String> queryCategory() {
        String sql = "SELECT category FROM poem GROUP BY category";
        return jdbcTemplate.queryForList(sql,String.class);
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Poem queryDetailById(String id) {
        String sql = "SELECT * FROM poem WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Poem.class),id);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
