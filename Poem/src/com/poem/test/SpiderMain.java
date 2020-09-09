package com.poem.test;

import com.poem.bean.Poem;
import com.poem.util.C3P0Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpiderMain {
    static int i = 0;
    static String url ="https://so.gushiwen.cn/gushi/tangshi.aspx",str = "唐诗三百首";
    //static String url = "https://so.gushiwen.cn/gushi/sanbai.aspx",str = "古诗三百首";
    //static String url = "https://so.gushiwen.cn/gushi/songsan.aspx",str = "宋词三百首";
    // static String url = "https://so.gushiwen.cn/gushi/chuci.aspx",str = "楚辞";
    // static String  url="https://so.gushiwen.cn/gushi/yuefu.aspx",str="乐府";
    // static String url = "https://so.gushiwen.cn/gushi/shijing.aspx",str = "诗经";

    /**
     * 爬取古诗文网站信息
     */
    public static void main(String[] args) throws IOException, InterruptedException {
       // 获取首页所有a标签的href属性
        ArrayList<Object> urls = getUrlsFromIndex(url);
        /*for (Object o : urls) {
            System.out.println(o);
        }*/

        // 从url中获取数据
        ArrayList<Object> poemList = getPoemFromUrls(urls);
        
        // 把数据存储到数据库中
        savePoemToDb(poemList);

        System.out.println("插入完成");
        
    }

    /**
     * 把数据存储到数据库中
     * @param poemList
     */
    private static void savePoemToDb(ArrayList<Object> poemList) {
        // 初始化JdbcTemplate模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());
        String sql = "INSERT INTO poem VALUES(NULL,?,?,?,?,?)";

        // 批量插入的方法
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Poem poem = (Poem) poemList.get(i);
                preparedStatement.setString(1,poem.getTitle());
                preparedStatement.setString(2,poem.getDynasty());
                preparedStatement.setString(3,poem.getAuthor());
                preparedStatement.setString(4,poem.getContent());
                preparedStatement.setString(5,poem.getCategory());
                System.out.println(i+poem.getTitle()+": 插入完成");
            }

            @Override
            public int getBatchSize() {
                return poemList.size();
            }
        });

    }

    /**
     * 从urls中解析每一首诗文内容
     * @param urls
     * @return
     */
    private static ArrayList<Object> getPoemFromUrls(ArrayList<Object> urls) throws IOException, InterruptedException {
        // 创建list封装诗词对象
        ArrayList<Object> poemList = new ArrayList<>();
        // Poem poem = null;
        if (null != urls){
            // 解析数据
            for (Object url : urls) {
                // 发生请求，获取诗词详情
                Document doc = Jsoup.connect((String) url)
                        .userAgent("")
                        .timeout(50000)
                        .get();
                // System.out.println(doc);

                // 诗词整体内容
                Element element = doc.select("div.sons div.cont").first();
                // 诗词标题
                String title = element.select("h1").first().text();
                // 朝代和作者
                String dyAndAuthor = element.select("p.source a").text();
                String dynasty = dyAndAuthor.split(" ")[0];
                String author = dyAndAuthor.split(" ")[1];
                // 诗词内容
                String content = element.select("div.contson").text();

                Poem poem = new Poem();
                poem.setTitle(title);
                poem.setDynasty(dynasty);
                poem.setAuthor(author);
                poem.setContent(content);
                poem.setCategory(str);

                // 把poem封装到list中
                poemList.add(poem);


                System.out.println(++i + title);
                // System.out.println(dyAndAuthor);
                // System.out.println(dynasty);
                // System.out.println(author);
                // System.out.println(content);

                // Thread.sleep(1000);
            }
        }
        return poemList;
    }

    private static ArrayList<Object> getUrlsFromIndex(String url) throws IOException {
        // 创建一个List
        ArrayList<Object> urls = new ArrayList<>();

        // 1.和古诗文网站建立连接
        Connection conn = Jsoup.connect(url);
        // 2.设置请求参数，伪装
        conn.userAgent("");
        conn.timeout(5000);

        // 3.爬取数据
        Document doc = conn.get();
        // System.out.println(doc);

        // 4.解析数据
        Elements elements = doc.select("div.typecont a");
        for (Element element : elements) {
            // System.out.println("https://m.gushiwen.cn/"+element.attr("href"));
            // Thread.sleep(1000);
            urls.add("https://so.gushiwen.cn/"+element.attr("href"));
        }

        return urls;
    }
}
