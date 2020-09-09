package com.poem.web;

import com.poem.service.PoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/queryDetailByIdServlet")
public class QueryDetailByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求出参数
        String id = req.getParameter("id");
        // 2.处理数据，根据id查询诗词详情
        PoemService poemService = new PoemService();
        String poemDetailList = poemService.queryDetailById(id);
        // 3.响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().println(poemDetailList);
    }
}
