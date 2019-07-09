package pachong;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class PriceDetailJsoup {


    public static void main(String[] args) {
        List<String> urls = UrlUtils.urls();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("E:/prices/result/prices001.txt"));

            //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置
            WebClient browser = new WebClient();
            browser.getOptions().setCssEnabled(false);
            browser.getOptions().setJavaScriptEnabled(true);
            browser.getOptions().setThrowExceptionOnScriptError(false);
            browser.getOptions().setThrowExceptionOnFailingStatusCode(false);
            //设置等待js的加载时间
            browser.waitForBackgroundJavaScript(10000);
            for (int i = 0; i < urls.size(); i++) {
                //获取页面
                HtmlPage htmlPage = browser.getPage(urls.get(i));


                //使用xml的方式解析获取到jsoup的document对象
                Document document = Jsoup.parse(htmlPage.asXml());


                String text = document.select("#content > p").text();
                //System.out.println(text);


                Elements elements = document.select("#prGrid > tbody > tr");

                if (null != elements && !elements.isEmpty()) {
                    if (elements.size()>1){
                        System.out.println(text );
                    }
                    for (Element element : elements) {
                        writer.write(text + "\t" + element.text() + "\n");
                    }
                } else {
                    writer.write(text+"\n");
                }


            }
            browser.close();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
