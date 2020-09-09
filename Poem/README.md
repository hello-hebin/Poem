## 古诗文项目1.0

### 一、项目概要
1. 数据来源：古诗文数据采用Jsoup爬虫技术爬取，[古诗文网](https://www.gushiwen.org/gushi/)
2. 前端页面：
 使用`bootstrap-3.3.5\docs\examples\offcanvas`的模板
 导入`offcanvas.cssy，offcanvas.js，getParameter.js，jquery，bootstrap`等文件

3. 后端技术：
		数据库交互使用C3P0连接池，Spring的JdbcTemplate模板
		使用MVC开发模式，web-->service-->dao
		
### 二、项目展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200909110500322.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDUwNTE5NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200909110541420.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDUwNTE5NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200909110607656.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDUwNTE5NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200909112212878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDUwNTE5NA==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200909112333230.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDUwNTE5NA==,size_16,color_FFFFFF,t_70#pic_center)

三、项目制作流程复现
1. 数据爬取，Jsoup爬取数据，并持久化到数据库
2. 前端页面，web目录下 index.html，poem_detail.html，poem_list.html
3. 后端逻辑，servlet -->service -->dao


四、项目打包发布至[Github]()