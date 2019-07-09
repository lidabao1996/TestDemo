package pachong;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DrugPaths extends BreadthCrawler {
       public DrugPaths(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);

        this.addSeed("https://www.315jiage.cn");
        setThreads(50);
        getConf().setTopN(100);

    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();


        System.out.println();
        System.out.println();
        System.err.println("URL:   " + url);
        System.out.println();

        //#contioer
        Elements es = page.select(".top-sorts a");

        for(Element l : es) {
            System.out.println("https://www.315jiage.cn/" + l.attr("href"));
            //System.out.println(l.text());
        }


    }

    public static void main(String[] args) throws Exception {
        DrugPaths crawler = new DrugPaths("crawl", true);

        crawler.start(4);
    }
}
