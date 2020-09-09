package com.poem.web;

import com.poem.service.PoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/queryByCategoryServlet")
public class QueryByCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收请求数据
        String category = req.getParameter("category");
        // 2.处理数据，根据诗词类别查询数据
        PoemService poemService = new PoemService();
        // 返回值：字符串
        String poemList = poemService.queryByCategory(category);

        // 3.响应数据：JSON字符串
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().println(poemList);
    }
}
