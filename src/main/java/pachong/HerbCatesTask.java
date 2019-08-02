package pachong;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HerbCatesTask {
    static Connection connection = null;

    public static void main(String[] args) throws Exception {
        cates();
        // dingxiangDoctorCates("阿莫西林", setParams());
    }

    public static void cates() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://192.168.8.3:3306/drs";
        String user = "root";
        String password = "pro";

        connection = (Connection) DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select url from herb_url");

        //得到结果
        while (resultSet.next()) {
            //使用resultSet.next()判断是否有下一个值，如果有就返回true
            String genericUrl = resultSet.getString("url");
            //System.out.println(genericUrl);
            dingxiangDoctorCates(genericUrl, setParams());
        }
        //释放资源
        connection.close();
        statement.close();
        statement.close();
    }


    static PreparedStatement pstm = null;

    public static void dingxiangCate(String url) {
        try {

            pstm = connection.prepareStatement("insert into dingxiangyuan_herb_cate(generic_key,text)value(?,?)");
            Document doc = Jsoup.connect("http://drugs.dxy.cn/search/drug.htm?keyword=" + url).get();
            //http://drugs.dxy.cn/search/drug.htm?keyword=开胸理气丸
            doc.baseUri();
            String text = doc.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();
            //String text = doc.select("#container > div.common_bd.clearfix").text();

            System.out.println(text);
            if (!StringUtils.isEmpty(text)) {
                pstm.setString(1, url);
                pstm.setString(2, text);

                boolean row = pstm.execute();
                if (row) {
                    System.out.println("添加成功！");
                }
            }

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Document document = null;

    /**
     * http://drugs.dxy.cn/search/drug.htm?keyword=%E9%98%BF%E8%8E%AB%E8%A5%BF%E6%9E%97
     * 中药材分类
     */
    public static void dingxiangDoctorCates(String url, WebClient webClient) {
        try {
            // pstm = connection.prepareStatement("insert into dingxiangyuan_herb_cate(generic_key,text)value(?,?)");
            // 准备链接
            //WebRequest webRequest = new WebRequest(new URL(url));
            // 设置请求方式
            //webRequest.setHttpMethod(HttpMethod.GET);
            // 发送页面请求
            Thread.sleep(3000);
            HtmlPage page = webClient.getPage("http://drugs.dxy.cn/search/drug.htm?keyword=" + url);
            System.out.println("网页加载中....");

            document = Jsoup.parse(page.asXml());

            String text = document.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();
            if (!StringUtils.isEmpty(text)) {
                pstm.setString(1, url);
                pstm.setString(2, text);

                boolean row = pstm.execute();
                if (row) {
                    System.out.println("添加成功！");
                }
            } else {

                String pageTotal = document.select("#container > div.pagination > span").attr("title");

                if (null != pageTotal) {
                    String[] arr = pageTotal.split(", ");
                    String reg = "[^0-9]";
                    String num = arr[1];
                    Pattern p = Pattern.compile(reg);
                    Matcher m = p.matcher(num);
                    int total = Integer.parseInt(m.replaceAll("").trim());
                    if (total > 1) {
                        for (int i = 1; i <= total; i++) {
                            HtmlPage fenye = webClient.getPage("http://drugs.dxy.cn/search/drug.htm?page=" + i + "&keyword=" + url);
                            document = Jsoup.parse(fenye.asXml());
                            Elements elements = document.select("#container > div.common_bd.clearfix > div > div > div > ul >li");

                            for (Element element : elements) {
                                String attr = element.select("a").attr("href");
                                saveUrls(url, attr);
                            }
                        }
                    }
                }
            }

            // 线程沉睡
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 这是浏览器爬虫情况
     *
     * @return
     */
    public static WebClient setParams() {
        //设置浏览器模型
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //设置web对象的相关参数
        //1启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        //2禁用css，可避免自动第二次请求css进行渲染
        webClient.getOptions().setCssEnabled(false);

        //设置代理

        //忽略ssl认证
        webClient.getOptions().setUseInsecureSSL(true);

        // 3 启动重定向
        webClient.getOptions().setRedirectEnabled(true);
        //4启动重定向
        webClient.setCookieManager(new CookieManager());

        //5:启动ajax代理
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        //6:js运行错误，是否抛出异常

        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //7:设置超时
        webClient.getOptions().setTimeout(50000);

        //8设置js执行超时时间
        webClient.setJavaScriptTimeout(50000);

        return webClient;
    }

    public static void test() {

        File writename = null; // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename = new File("E:/ttt.txt");
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("我会写入文件啦,\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void saveUrls(String genericKey, String cateUrl) throws Exception {
        pstm = connection.prepareStatement("insert into herb_cates_urls(generic_key,cate_url)value(?,?)");
        if (!StringUtils.isEmpty(cateUrl)) {
            pstm.setString(1, genericKey);
            pstm.setString(2, cateUrl);

            boolean row = pstm.execute();
            if (row) {
                System.out.println("添加成功！");
            }
        }
    }


}
