package pachong;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.sql.PreparedStatement;

/**
 * 使用jsoup实现验证码问题
 */
public class DingxiangVerificationTask {
    static PreparedStatement pstm = null;


    public static void main(String[] args) {
        String[] urls = {"http://drugs.dxy.cn/search/drug.htm?keyword=阿胶三宝膏", "http://drugs.dxy.cn/search/drug.htm?keyword=黄芪"};
        for (int i = 0; i < urls.length; i++) {
            dingxiangDoctorCates(urls[i], setParams());
        }
    }

    /**
     * 中药材分类
     */
    public static void dingxiangDoctorCates(String url, WebClient webClient) {
        try {
            // 准备链接
            //WebRequest webRequest = new WebRequest(new URL(url));
            // 设置请求方式
            //webRequest.setHttpMethod(HttpMethod.GET);
            // 发送页面请求
            Thread.sleep(2000);
            HtmlPage page = webClient.getPage(url);
            System.out.println("网页加载中....");

            Document document = Jsoup.parse(page.asXml());

            String text = document.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();
            if (!StringUtils.isEmpty(text)) {
                pstm.setString(1, url);
                pstm.setString(2, text);

                boolean row = pstm.execute();
                if (row) {
                    System.out.println("添加成功！");
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

}
