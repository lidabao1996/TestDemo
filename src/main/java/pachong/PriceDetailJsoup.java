package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PriceDetailJsoup {

    static String GENERICENAME = "x-FuKe";

    public static void main(String[] args) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("E:/prices.txt"), "UTF-8");
            List<String> urls = urls();
            for (String url : urls) {
                try {
                    Document document = Jsoup.connect(url).get();
                    String text = document.select("#content > p").text();
                    writer.write(text + "\n");


                    System.out.println("text = " + text);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static List<String> urls() {
        String line;
        List<String> urls = new ArrayList<>();
        try {
            //FileInputStream inputStream = new FileInputStream("E:/url/"+GENERICENAME+"-url.txt");
            FileInputStream inputStream = new FileInputStream("E:/result.txt");

            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            while ((line = br.readLine()) != null) {
                urls.add(line);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }
}
