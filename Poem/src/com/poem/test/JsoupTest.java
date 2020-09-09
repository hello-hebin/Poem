package com.poem.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class JsoupTest {

    @Test
    public void test1() throws IOException {
        //Jsoup入门
        Document doc = Jsoup.connect("http://www.itcast.cn/").get();
        // System.out.println(doc);

        // 获取数据
        Elements elements = doc.getElementsByTag("a");
        for (Element element : elements) {
            System.out.println(element);
        }
    }

    @Test
    public void test2() throws IOException {
        // 1.使用Jsoup建立连接
        Connection conn = Jsoup.connect("http://www.itcast.cn/");

        // 2.设置请求参数，反爬虫
        conn.userAgent("");
        conn.timeout(5000);

        // 3.发送请求
        Document doc = conn.get();
        // String title = doc.title();
        // System.out.println(title);

        // 4.使用选择器解析页面数据
        // 4.1 选中含有href属性的标签
        /*Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            System.out.println(element);
        }*/

        // 4.2 使用选择器获取图片
        /*Elements elements = doc.select("img[src]");
        for (Element element : elements) {
            System.out.println(element.attr("src"));
        }*/

        // 4.3 获取.jpg的图片
        /*Elements elements = doc.select("img[src$=.jpg]");
        for (Element element : elements) {
            System.out.println(element.attr("src"));
        }*/
    }
}
