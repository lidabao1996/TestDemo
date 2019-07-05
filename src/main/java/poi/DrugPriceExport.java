package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DrugPriceExport {
    public static void main(String[] args) {
        String line;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("药品价格");


        XSSFRow row = null;
        XSSFCell cell = null;




        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:/prices.txt"))));
            FileOutputStream out = new FileOutputStream("E:/prices_excel.xlsx");


            while ((line = reader.readLine()) != null) {
                //line = [药品价格315网建议销售价格]　　零售价格：58.00元　　批发价格：0.00元　　价格趋势：平 产品名称：盛力源牌维生素C咀嚼片 (盛力源)　　拼音简码：SLYPWSSCJJP 规格：72g(1.2g*60片)　剂型：　包装单位：瓶 批准文号：国食健注G20170396 生产厂家：广州市绿健生物科技有限公司 条形码：6971501680012
                //line.replace("　　")
                line = line.replace("　", " ");
                String[] arr = line.split("\\s+");
                String str = null;
                Map<String, String> item = new HashMap<>();
                for (int i = 0; i < arr.length; i++) {

                    String s = arr[i];
                    if (i == 0) {
                        item.put("title", s);
                    } else if (str == null) {
                        str = s;
                    } else if (s.contains("：")) {
                        String[] kv = str.split("：");

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
                item.put(kv[0], kv[1]);
                row = sheet.createRow(item.size());
                cell = row.createCell(10);
                /*cell = row.createCell(0);
                cell.setCellValue("拼音简码");

                cell = row.createCell(1);
                cell.setCellValue("包装单位");
                cell = row.createCell(2);
                cell.setCellValue("主治疾病");
                cell = row.createCell(3);
                cell.setCellValue("剂型");
                cell = row.createCell(4);
                cell.setCellValue("生产厂家");
                cell = row.createCell(5);
                cell.setCellValue("零售价");
                cell = row.createCell(6);
                cell.setCellValue("价格趋势");
                cell = row.createCell(7);
                cell.setCellValue("产品名称");
                cell = row.createCell(8);
                cell.setCellValue("批发价格");
                cell = row.createCell(9);
                cell.setCellValue("批准文号");*/

                cell.setCellValue(kv[1]);


                System.out.println(item);

                System.out.println("--------------");

            }
            reader.close();
            wb.write(out);
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
