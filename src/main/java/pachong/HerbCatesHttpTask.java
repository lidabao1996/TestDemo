package pachong;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.mysql.jdbc.Connection;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 128.220.253.210:80
 */
public class HerbCatesHttpTask {
    static Connection connection = null;

    public static void main(String[] args) throws Exception {
        cates();
    }

    public static void cates() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://192.168.8.3:3306/drs";
        String user = "root";
        String password = "pro";

        connection = (Connection) DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select generic_key,cate_url from herb_cates_urls_temp");
        //得到结果
        while (resultSet.next()) {
            //使用resultSet.next()判断是否有下一个值，如果有就返回true
            String genericKey = resultSet.getString("generic_key");
            String genericUrl = resultSet.getString("cate_url");
            //System.out.println(genericUrl);
            dingxiangDoctorCates(genericKey, genericUrl, setParams());
        }
        //释放资源
        connection.close();
        statement.close();
        statement.close();
    }

    static PreparedStatement pstm = null;
    public static Document document = null;

    /**
     * http://drugs.dxy.cn/search/drug.htm?keyword=%E9%98%BF%E8%8E%AB%E8%A5%BF%E6%9E%97
     * 中药材分类
     */
    public static void dingxiangDoctorCates(String genericKey, String url, WebClient webClient) {
        try {
            pstm = connection.prepareStatement("insert into dingxiangyuan_herb_cate(generic_key,text)value(?,?)");

            herbCateTask(genericKey,url);


            /*System.setProperty("http.proxyHost", "128.220.253.210");
            System.setProperty("http.proxyPort", "80");


            // 发送页面请求
            HtmlPage page = webClient.getPage("http:" + url);
            System.out.println("网页加载中....");

            document = Jsoup.parse(page.asXml());

            String text = document.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();
            if (!StringUtils.isEmpty(text)) {
                pstm.setString(1, genericKey);
                pstm.setString(2, text);

                boolean row = pstm.execute();
                if (row) {
                    System.out.println("添加成功！");
                }
            } else {

                String pageTotal = document.select("#container > div.pagination > span").attr("title");

                if (!StringUtils.isEmpty(pageTotal)) {
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
                } else {
                    Elements elements = document.select("#container > div.common_bd.clearfix > div > div > div > ul >li");
                    for (Element element : elements) {
                        String attr = element.select("a").attr("href");
                        saveUrls(url, attr);
                    }
                }
            }

            // 线程沉睡
            Thread.sleep(1000);*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
       /* ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
        proxyConfig.setProxyHost("128.220.253.210");
        proxyConfig.setProxyPort(80);*/


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


    public static void herbCateTask(String genericKey, String url) throws Exception {
        /*System.setProperty("http.proxyHost", "128.220.253.210");
        System.setProperty("http.proxyPort", "80");*/
        //218.193.191.192 ：8888
        System.setProperty("http.proxyHost", "218.193.191.192");
        System.setProperty("http.proxyPort", "8888");
        Document document = Jsoup.connect("http:" +url).timeout(10000).get();
        String text = document.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();
        if (!StringUtils.isEmpty(text)) {
            pstm.setString(1, genericKey);
            pstm.setString(2, text);

            boolean row = pstm.execute();
            if (row) {
                System.out.println(genericKey+":添加成功");
            }
        }else {
            System.out.println(genericKey+":没成功");
            System.out.println("失败原因:"+document);
        }

        // 线程沉睡
        Thread.sleep(1000);
    }

}
