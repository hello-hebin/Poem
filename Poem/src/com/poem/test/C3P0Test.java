package com.poem.test;

import com.poem.bean.Poem;
import com.poem.util.C3P0Utils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class C3P0Test {

    @Test
    public void C3P0Test(){
        Poem poem = new Poem();
        poem.setTitle("静夜思");
        poem.setDynasty("唐");
        poem.setAuthor("李白");
        poem.setContent("床前明月光，疑是地上霜。巨头网映月，低头是故乡。");
        poem.setCategory("唐诗三百首");

        System.out.println(poem.toString());

        // 初始化JdbcTemplate模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());
        String sql = "insert into poem values(null,'"+poem.getTitle()+"','"+poem.getDynasty()+"','"+poem.getAuthor()+"','"+poem.getContent()+"','"+poem.getCategory()+"')";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void C3P0Test2(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());
        String sql = "SELECT category FROM poem GROUP BY category";
        List<String> strings = jdbcTemplate.queryForList(sql, String.class);
        System.out.println(strings);
    }
}
