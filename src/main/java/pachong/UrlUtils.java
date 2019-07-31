package pachong;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UrlUtils {
    public int id;

    public int getId() {
        return id;
    }



    public static List<String> urls() {
        String line;
        List<String> urls = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream("E:/prices/url_result.txt");

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
