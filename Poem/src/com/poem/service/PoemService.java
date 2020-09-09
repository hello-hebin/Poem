package com.poem.service;

import com.alibaba.fastjson.JSON;
import com.poem.bean.Poem;
import com.poem.dao.PoemDao;

import java.util.List;

/**
 * 处理诗词业务的service
 */
public class PoemService {

    private PoemDao poemDao = new PoemDao();

    /**
     * 根据category查询诗词列表
     * @param category
     * @return
     */
    public String queryByCategory(String category) {
        // 调用dao层
        List<Poem> poemList = poemDao.queryByCategory(category);
        return JSON.toJSONString(poemList);
    }

    /**
     * 查询诗词类别信息
     * @return
     */
    public String queryCategory() {
        List<String> categoryList = poemDao.queryCategory();
        return JSON.toJSONString(categoryList);
    }

    /**
     * 根据id查询诗词详情
     * @param id
     * @return
     */
    public String queryDetailById(String id) {
        Poem poem = poemDao.queryDetailById(id);
        return JSON.toJSONString(poem);
    }
}
