package pachong;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class URLRead {
    public static void main(String[] args) {
        String line;
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufReader=new BufferedReader(new  InputStreamReader (new FileInputStream(new File("E:/drug/x-FuKe.txt")),"UTF-8"));
            while ((line = bufReader.readLine()) !=null){
                String [] arr = line.split("   ../");
                String url = arr[1];
                list.add("https://www.315jiage.cn/" + url);
                System.out.println("https://www.315jiage.cn/" + url);
            }


            System.out.println("list.size() = " + list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
