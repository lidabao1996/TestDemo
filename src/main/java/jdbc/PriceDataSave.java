package jdbc;

import com.mysql.jdbc.Connection;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.taskdefs.optional.jsp.WLJspc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceDataSave {


    public static void save(PreparedStatement pstm, Connection conn) {

        List<Map<String, String>> list = getPriceData();
        try {
            String sql = "insert into catch_drug_price(drug_title,batch_price,pifajia,price_qushi,price_name,pinyinjianma,guige,jixing,baozhuangdanwei,wenhao,changjia,tiaoxingma,zhuzhijibing,product_standard)value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String drug_title;
            String batch_price;
            String pifajia;
            String price_qushi;
            String price_name;
            String pinyinjianma;
            String guige;
            String jixing;
            String baozhuangdanwei;
            String wenhao;
            String changjia;
            String tiaoxingma;
            String zhuzhijibing;
            String product_standard;
            for (int i = 0; i <= list.size(); i++) {
                pstm = conn.prepareStatement(sql);
                Map<String, String> map = list.get(i);
                drug_title = map.get("title");
                batch_price = map.get("batch_price");
                pifajia = map.get("pifajia");
                price_qushi = map.get("price_qushi");
                price_name = map.get("price_name");
                pinyinjianma = map.get("pinyinjianma");
                guige = map.get("guige");
                jixing = map.get("jixing");
                baozhuangdanwei = map.get("baozhuangdanwei");
                wenhao = map.get("wenhao");
                changjia = map.get("changjia");
                tiaoxingma = map.get("tiaoxingma");
                zhuzhijibing = map.get("zhuzhijibing");
                product_standard = map.get("product_standard");
                pstm.setString(1, drug_title);
                pstm.setString(2, batch_price);
                pstm.setString(3, pifajia);
                pstm.setString(4, price_qushi);
                pstm.setString(5, price_name);
                pstm.setString(6, pinyinjianma);
                if (StringUtils.isEmpty(guige)) {
                    pstm.setString(7, "");
                } else {
                    pstm.setString(7, guige);
                }

                if (StringUtils.isEmpty(jixing)) {
                    pstm.setString(8, "");
                } else {
                    pstm.setString(8, jixing);
                }

                if (StringUtils.isEmpty(baozhuangdanwei)) {
                    pstm.setString(9, "");
                } else {
                    pstm.setString(9, baozhuangdanwei);
                }

                if (StringUtils.isEmpty(wenhao)) {
                    pstm.setString(10, "");
                } else {
                    pstm.setString(10, wenhao);
                }

                if (StringUtils.isEmpty(changjia)) {
                    pstm.setString(11, "");
                } else {
                    pstm.setString(11, changjia);
                }

                if (StringUtils.isEmpty(tiaoxingma)) {
                    pstm.setString(12, "");
                } else {
                    pstm.setString(12, tiaoxingma);
                }

                if (StringUtils.isEmpty(zhuzhijibing)) {
                    pstm.setString(13, "");
                } else {
                    pstm.setString(13, zhuzhijibing);
                }

                if (StringUtils.isEmpty(product_standard)) {
                    pstm.setString(14, "");
                } else {
                    pstm.setString(14, product_standard);
                }
                boolean row = pstm.execute();
                if (row) {
                    System.out.println("添加成功！");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Map<String, String>> getPriceData() {
        List<Map<String, String>> list = new ArrayList<>();

        try {
            InputStream in = new FileInputStream("C:/Users/lifang/Desktop/price.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(in);

            XSSFSheet sheet = wb.getSheetAt(0);

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                XSSFRow row = sheet.getRow(rowNum);

                XSSFCell cell1 = row.getCell(0);
                XSSFCell cell2 = row.getCell(1);
                XSSFCell cell3 = row.getCell(2);
                XSSFCell cell4 = row.getCell(3);
                XSSFCell cell5 = row.getCell(4);
                XSSFCell cell6 = row.getCell(5);
                XSSFCell cell7 = row.getCell(6);
                XSSFCell cell8 = row.getCell(7);
                XSSFCell cell9 = row.getCell(8);
                XSSFCell cell10 = row.getCell(9);
                XSSFCell cell11 = row.getCell(10);
                XSSFCell cell12 = row.getCell(11);
                XSSFCell cell13 = row.getCell(12);
                XSSFCell cell14 = row.getCell(13);
                Map<String, String> vo = new HashMap<>();
                if (null != cell1) {
                    vo.put("title", cell1.getStringCellValue().trim());
                }

                if (null != cell2) {
                    vo.put("batch_price", cell2.getStringCellValue().trim());
                }
                if (null != cell3) {
                    vo.put("pifajia", cell3.getStringCellValue().trim());
                }

                if (null != cell4) {
                    vo.put("price_qushi", cell4.getStringCellValue().trim());
                }
                if (null != cell5) {
                    vo.put("price_name", cell5.getStringCellValue().trim());
                }
                if (null != cell6) {
                    vo.put("pinyinjianma", cell6.getStringCellValue().trim());
                }
                if (null != cell7) {
                    vo.put("guige", cell7.getStringCellValue().trim());
                }
                if (null != cell8) {
                    vo.put("jixing", cell8.getStringCellValue().trim());
                }
                if (null != cell9) {
                    vo.put("baozhuangdanwei", cell9.getStringCellValue().trim());
                }
                if (null != cell10) {
                    vo.put("wenhao", cell10.getStringCellValue().trim());
                }

                if (null != cell11) {
                    vo.put("changjia", cell11.getStringCellValue().trim());
                }

                if (null != cell12) {
                    vo.put("tiaoxingma", cell12.toString());
                }

                if (null != cell13) {
                    vo.put("zhuzhijibing", cell13.getStringCellValue().trim());
                }

                if (null != cell14) {
                    vo.put("product_standard", cell14.getStringCellValue().trim());
                }

                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

}
