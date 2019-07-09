package poi;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrugPriceExportExcel {
    public static void main(String[] args) {
        String line;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:/prices.txt"))));
            List<String> titles = new ArrayList<>();
            titles.add("标题");
            List<Map<String,Object>> items= new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                //line = [药品价格315网建议销售价格]　　零售价格：58.00元　　批发价格：0.00元　　价格趋势：平 产品名称：盛力源牌维生素C咀嚼片 (盛力源)　　拼音简码：SLYPWSSCJJP 规格：72g(1.2g*60片)　剂型：　包装单位：瓶 批准文号：国食健注G20170396 生产厂家：广州市绿健生物科技有限公司 条形码：6971501680012
                //line.replace("　　")
                line = line.replace("　", " ");
                String[] arr = line.split("\\s+");
                String str = null;
                Map<String, Object> item = new HashMap<>();

                for (int i = 0; i < arr.length; i++) {

                    String s = arr[i];
                    if (i == 0) {
                        item.put("标题", s);
                    } else if (str == null) {
                        str = s;
                    } else if (s.contains("：")) {
                        String[] kv = str.split("：");
                        if(!titles.contains(kv[0])){
                            titles.add(kv[0]);
                        }
                        if (kv.length < 2) {
                            item.put(kv[0], "");
                        } else {
                            item.put(kv[0], kv[1]);
                        }
                        str = s;
                    } else {
                        str = str + s;
                    }
                }
                String[] kv = str.split("：");
                if(!titles.contains(kv[0])){
                    titles.add(kv[0]);
                }
                item.put(kv[0], kv[1]);
                items.add(item);

                //System.out.println(item);

                //System.out.println("--------------");

            }
            reader.close();

            FileOutputStream out = new FileOutputStream("E://prices.csv");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
            writer.write(getBOM());
            for(String title:titles){
                writer.print("\t"+title+"\t,");
            }
            writer.print("\n");
            for(Map<String,Object> item:items){
                for(String title:titles){
                    writer.print("\t"+(item.get(title) == null ? "" : item.get(title))+"\t,");
                }
                writer.print("\n");
            }

            writer.flush();
            writer.close();

            System.out.println("写完");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能说明：获取UTF-8编码文本文件开头的BOM签名。
     * BOM(Byte Order Mark)，是UTF编码方案里用于标识编码的标准标记。例：接收者收到以EF BB BF开头的字节流，就知道是UTF-8编码。
     * @return UTF-8编码文本文件开头的BOM签名
     */
    public static String getBOM() {

        byte b[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
        return new String(b);
    }

}
