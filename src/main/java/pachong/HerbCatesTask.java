package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HerbCatesTask {
    public static void main(String[] args) throws Exception {
        cates();

    }

    public static void cates() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://192.168.8.3:3306/drs";
        String user = "root";
        String password = "pro";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select url from herb_url");

        //得到结果
        while (resultSet.next()) {
            //使用resultSet.next()判断是否有下一个值，如果有就返回true
            String genericUrl = resultSet.getString("url");
            dingxiangCate(genericUrl);
        }
        //释放资源
        connection.close();
        statement.close();
        statement.close();
    }


    public static void dingxiangCate(String url) {
        try {
            Document doc = Jsoup.connect("http://drugs.dxy.cn/search/drug.htm?keyword=" + url).get();
            doc.baseUri();
            String text = doc.select("#container > div.common_bd.clearfix > div.common_mainwrap.fl > div > div").text();

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("E:/cates.txt"));
            writer.write(text+"\n");
            System.out.println(text);

            writer.flush();
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
