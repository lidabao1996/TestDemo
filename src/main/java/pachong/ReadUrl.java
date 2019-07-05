package pachong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadUrl {
    public static void main(String[] args) {
        readFile(new File("E:/drug"));
    }

    static void readFile(File file) {
        FileInputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        String line;
        if (file == null || !file.exists()) {
            return;
        }


        if (file.isFile()) {
            System.out.println("isFile");
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println("文件名:" + f.getName());
                try {
                    inputStream = new FileInputStream("E:/drug/"+f.getName());
                    reader = new InputStreamReader(inputStream, "UTF-8");
                    br = new BufferedReader(reader);

                    while ((line = br.readLine()) != null) {
                        //System.out.println("line = " + line);
                        String [] arr = line.split("   ../");
                        String url = arr[1];
                        System.out.println("https://www.315jiage.cn/" + url);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

       // System.out.println("file.getName() = " + file.getName());
    }
}
