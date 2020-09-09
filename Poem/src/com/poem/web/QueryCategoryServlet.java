package com.poem.web;

import com.poem.service.PoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/queryCategoryServlet")
public class QueryCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收请求数据，无数据
        // 2.处理数据
        PoemService poemService = new PoemService();
        // 查询诗词的类别信息
       String categoryJson = poemService.queryCategory();

       // 3.响应数据
       //  处理中文乱码
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().println(categoryJson);
    }
}
