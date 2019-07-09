package pachong;

import java.io.*;

public class Main {
    public static void main(String[] args) {

//        count("D:\\kingyee\\med_guideline\\medlive_guideline_android_v4");
//        System.out.println("total lines:" + count);
        write();
    }

    private static void write() {
        File file = new File("E:/drug");
        File result = new File("E:/prices/url_result.txt");
        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File[] sub = file.listFiles();
        int count = 0;
        try {
            FileOutputStream fos = new FileOutputStream(result);
            for (int i = 0; i < sub.length; i++) {
                File f = sub[i];
                //System.out.println(f.getName());
                BufferedReader br = new BufferedReader(new FileReader(f));
                while (br.read() != -1) {
                    String line = br.readLine();
                    if (line == null || "".equals(line) || "\r\n".equals(line)) {
                        continue;
                    }
                    String suffix = line.substring(line.indexOf("../") + 3);
                    if (suffix.equals("z-XueYeXi/294834.htm")){
                        System.out.println(f.getName());
                    }
                    String url = "https://www.315jiage.cn/" + suffix + "\r\n";
                    line = line + "\r\n";
                    fos.write(url.getBytes(), 0, url.getBytes().length);
                    fos.flush();
                    count++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("total lines:" + count);

    }

    static int count = 0;

    private static void count(String path) {
        File file = new File(path);
        try {
            if (file.isDirectory()) {
                File[] sub = file.listFiles();
                for (int i = 0; i < sub.length; i++) {
                    File f = sub[i];
                    if (f.isDirectory()) {
                        count(f.getAbsolutePath());
                    } else {
                        countFile(f);
                    }
                }
            } else {
                countFile(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void countFile(File file) throws IOException {
        if (file.getName().contains("RecyclerView")
                || file.getName().equals("R.java")) {
            return;
        }
        if (file.getName().endsWith("java") || file.getName().endsWith("kt")) {
            System.out.println(file.getName());
            BufferedReader br = new BufferedReader(new FileReader(file));
            do {
                String line = br.readLine();
                count++;
            } while (br.read() != -1);
        }
    }
}
