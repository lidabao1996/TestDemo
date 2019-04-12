package pachong;

import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
//import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;

public class AutoNewsCrawler2 extends BreadthCrawler {

	public AutoNewsCrawler2(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);

		this.addSeed("https://www.autohome.com.cn/bestauto/#pvareaid=3311236");

		setThreads(50);
		getConf().setTopN(100);

	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.url();
		
		System.out.println();
		System.out.println();
		System.err.println("URL:   " + url);
		System.out.println();
		
		//先获取每辆车整个最大的容器
		Elements es = page.select("div.uibox");
		for(Element l : es) {
			//车型
			System.out.println("车型:  " + l.select("div.uibox").select("div.uibox-title").select("a").html());
			//获取车型里面每个人点评
			for(Element s:l.select("dl.fn-clear dd")) {
				System.err.println("姓名：   "+ s.select("div.dd-div1").select("a").html());
				System.err.println("正文：   " + s.select("div.dd-div3-pp").html());
				System.out.println();
				System.out.println();
			}
			
			
		}
	

	}

	public static void main(String[] args) throws Exception {
		AutoNewsCrawler2 crawler = new AutoNewsCrawler2("crawl", true);

		crawler.start(4);
	}

}
