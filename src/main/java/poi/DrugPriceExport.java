package poi;

import javafx.scene.control.Cell;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class DrugPriceExport {
    public static void main(String[] args) {
        String line;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        List<Map<String, String>> list = new ArrayList<>();

        String[] header = {"拼音简码", "包装单位", "条形码", "剂型", "生产厂家", "零售价", "价格趋势", "产品名称", "规格", "批发价格", "批准文号"};
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:/prices.txt"))));
            while ((line = reader.readLine()) != null) {
                //line = [药品价格315网建议销售价格]　　零售价格：58.00元　　批发价格：0.00元　　价格趋势：平 产品名称：盛力源牌维生素C咀嚼片 (盛力源)　　拼音简码：SLYPWSSCJJP 规格：72g(1.2g*60片)　剂型：　包装单位：瓶 批准文号：国食健注G20170396 生产厂家：广州市绿健生物科技有限公司 条形码：6971501680012
                //line.replace("　　")
                line = line.replace("　", " ");
                String[] arr = line.split("\\s+");
                String str = null;
                Map<String, String> item = new HashMap<>();
                List<String> itemStr = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {

                    String s = arr[i];
                    if (i == 0) {
                        //item.put("title", s);
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

                list.add(item);
                System.out.println(item);

                //System.out.println("--------------");

            }
            reader.close();

            XSSFRow rowHeader = sheet.createRow(0);

            for (int cellNum = 0; cellNum < header.length; cellNum++) {
                XSSFCell cell = rowHeader.createCell(cellNum);
                cell.setCellValue(header[cellNum]);
            }


            for (int rownum = 1; rownum < list.size(); rownum++) {
                XSSFRow row = sheet.createRow(rownum);
                Map<String, String> item = list.get(rownum);
                Set<String> keys = item.keySet();
                Iterator<String> iterator = keys.iterator();
                int cellNum = 0;
                while (iterator.hasNext()) {
                    XSSFCell cell = row.createCell(cellNum);
                    String value = item.get(iterator.next());
                    if (null == value) {

                        value = "";
                    }


                    if (cellNum == 0) {
                        cell.setCellValue(value);
                    } else if (cellNum == 1) {
                        cell.setCellValue(value);
                    } else if (cellNum == 2) {
                        cell.setCellValue(value);
                    } else if (cellNum == 3) {
                        cell.setCellValue(value);
                    } else if (cellNum == 4) {
                        cell.setCellValue(value);
                    } else if (cellNum == 5) {
                        cell.setCellValue(value);
                    } else if (cellNum == 6) {
                        cell.setCellValue(value);
                    } else if (cellNum == 7) {
                        cell.setCellValue(value);
                    } else if (cellNum == 8) {
                        cell.setCellValue(value);
                    } else if (cellNum == 9) {
                        cell.setCellValue(value);
                    }


                    cellNum++;
                }
            }

            OutputStream outputStream = new FileOutputStream("E:/价格.xlsx");
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
