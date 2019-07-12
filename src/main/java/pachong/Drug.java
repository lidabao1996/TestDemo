package pachong;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Drug extends BreadthCrawler {
    public Drug(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        addSeed("https://www.315jiage.cn/");
//        addRegex("https://www.315jiage.cn/[a-z-{0,1}A-z]+/([0-9]+.htm){0,1}(defaultp\\d{0,9}.htm){0,1}");
        addRegex("https://www.315jiage.cn/[a-z-{0,1}A-Z]+/$");

        setThreads(50);
        getConf().setTopN(100);
        getConf().setConnectTimeout(10 * 1000);
        getConf().setReadTimeout(10 * 1000);

    }

    int count = 0;

    @Override
    public Page getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum);
        Proxys proxies = new Proxys();
        proxies.add("218.193.191.192",8888);
        request.setProxy(proxies.nextRandom());
        //InetSocketAddress address = new InetSocketAddress("180.118.86.25", 9000);
        //request.setProxy(new Proxy(Proxy.Type.HTTP,address));
        return request.responsePage();
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();
        if (url.equals("https://www.315jiage.cn/")) {
            return;
        }


        System.out.println();
        System.out.println();
        System.err.println("URL---------------------------------------------------------:   " + url);
//        System.out.println(count++);


        String total = page.selectText(".container table .p_total");
        String[] split = total.split("/");
        int totalNum = Integer.parseInt(split[1]);
        System.out.println(url + " -----------" + "totalNum = " + totalNum);

        ArrayList<String> names = map.get(url);
        if (names == null) {
            names = new ArrayList<>();
            map.put(url, names);
        }
        for (int i = 1; i <= totalNum; i++) {
            try {
                Document doc = Jsoup.connect(url + "defaultp" + i + ".htm").get();
                //String text = doc.select("#content > p").text();

                Elements es = doc.select("div.sCard");
                for (Element l : es) {
                    names.add(l.text() + "   " + l.select("a").attr("href"));
                }

                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public static Map<String, ArrayList<String>> map = new HashMap<>();
    static OutputStreamWriter outputStream = null;


    public static void main(String[] args) throws Exception {
        Drug crawler = new Drug("crawl", true);
        crawler.start(2);


        int totalSize = 0;
        Set<Map.Entry<String, ArrayList<String>>> entries = map.entrySet();
        for (Map.Entry<String, ArrayList<String>> en : entries) {
            String name = en.getKey();
            ArrayList<String> value = en.getValue();
            System.out.println("name:" + name + " size=" + value.size());

            for (String fileText : value) {
                System.out.println(fileText);
            }


            String[] arr = name.split("https://www.315jiage.cn/");

            String filename = arr[1];
            filename = filename.substring(0, filename.length() - 1);
            outputStream = new OutputStreamWriter(new FileOutputStream("E:/drug2019/" + filename + ".txt"), "UTF-8");

            for (String fileText : value) {
                outputStream.write(fileText + "\n");
            }


            totalSize += value.size();
            outputStream.flush();
            outputStream.close();
        }


        System.out.println("totalSize=" + totalSize);
    }
}
