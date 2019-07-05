package pachong;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.apache.commons.lang.StringUtils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DrugPriceOutput extends BreadthCrawler {
    static List<String> prices = new ArrayList<String>();
    static List<String> urls = new ArrayList<>();
    public DrugPriceOutput(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);


        addSeed("https://www.315jiage.cn/");
        addRegex("https://www.315jiage.cn/[a-z-{0,1}A-z]+/([0-9]+.htm){0,1}(defaultp\\\\d{0,9}.htm){0,1}");
        //addRegex("https://www.315jiage.cn/[a-z-{0,1}A-Z]+/([1-9]+.htm){0,1}");

        setThreads(10);
        getConf().setTopN(100);
        getConf().setConnectTimeout(10*1000);
        getConf().setReadTimeout(10*1000);

    }


    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();
        urls.add(url);






        if (null == page.select("#content > p")) {
            return;
        }

        //药品名称
        String content = page.select("#content > p").text();

        if (null != content && StringUtils.isNotEmpty(content)) {
            prices.add(content);
        }


        //System.out.println( content);


        /*System.out.println("  ");
        System.out.println("  ");

        Elements lis = page.select("#tab1 > ul > li");
        for (Element li:lis){
            System.out.println(li.text());
        }*/


    }


    public static void main(String[] args) throws Exception {




        DrugPriceOutput crawler = new DrugPriceOutput("crawl", true);
        crawler.start(3);

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("E:/price003.txt"), "UTF-8");
        OutputStreamWriter urlWriter = new OutputStreamWriter(new FileOutputStream("E:/urls001.txt"), "UTF-8");
        System.out.println(prices.size()+"------------------------------------------------------------------");
        System.out.println(urls.size()+"------------------------------------------------------------------");
        if (null != prices || prices.size() > 0) {
            for (String price : prices) {
                writer.write(price + "\n");
            }
            writer.flush();
            writer.close();
        }



        if (null != urls || urls.size() > 0) {
            for (String url : urls) {
                urlWriter.write(url + "\n");
            }
            urlWriter.flush();
            urlWriter.close();
        }

    }
}
