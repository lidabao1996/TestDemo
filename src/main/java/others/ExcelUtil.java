package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel工具类  与hoss中的excel有所区别  hoss中POIExceUtils正是这个原版,但是没有提供读取的功能  hoss中读取的主要是由jxl读取的
 * 全部使用POIExcel读写
 * @author hyz.persist
 *
 */
/**
 * 这里需要注意一下几点
 * 1.我个人认为cell会在创建的时候有一个默认的样式,而这个默认的样式往往是公共的就会导致  如果改变的这个样式就会影响其他单元格
 *   所以每当创建一个单元格的时候最好级联创建一个样式  即为一个单元格对应一个样式,最好不要公共用
 * 2.不共用样式带来的好处
 *   做excel的时候经常需要把某个区域设置相同的样式,并且最希望的是这个添加景区的样式不能影响原来的样式,更不能影响其他的单元格
 *   如果非要用同一个样式  一定要注意以后这种格式不会再改变
 * 3.强烈建议一个单元格对应一个样式  这样为后期维护也会带来方便 
 * 这里就特意的封装了一些设置某个区域的样式
 * 
 * 提供设置打印属性的sheet
 * 提供了创建默认的单元格 设置基本属性单元格边框
 * 
 * 改进  增加读取方法 
 * 减少样式的使用  以免excel崩溃
 * 
 * 测试工具类:ExcelUtilTest
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {
	/**
	 * 获取单元格
	 * @param r
	 * @param c
	 * @param sheet
	 * @return
	 */
	public static HSSFCell getCell(int r,int c,HSSFSheet sheet){
		HSSFRow row = HSSFCellUtil.getRow(r, sheet);
		return HSSFCellUtil.getCell(row, c);
	}
	/**
	 * 获取行
	 * @param r
	 * @param sheet
	 * @return
	 */
	public static HSSFRow getRow(int r,HSSFSheet sheet){
		return HSSFCellUtil.getRow(r, sheet);
	}
	/**
	 * 创建默认的单元格
	 * @param r
	 * @param c
	 * @param sheet
	 * @return
	 */
	public static HSSFCell createCell(int r,int c,HSSFSheet sheet){
		HSSFCell cell = getRow(r, sheet).createCell(c);
		HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		setBorder(cellStyle);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 创建默认的单元格
	 * @param row
	 * @param c
	 * @return
	 */
	public static HSSFCell createCell(HSSFRow row,int c){
		HSSFCell cell = row.createCell(c);
		HSSFCellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
		setBorder(cellStyle);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 批量创建默认的单元格
	 * @param fromRow
	 * @param fromCol
	 * @param toRow
	 * @param toCol
	 * @param sheet
	 */
	public static void createCells(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		for(int i=fromRow;i<=toRow;i++){
			HSSFRow row = getRow(i, sheet);
			for(int j=fromCol;j<=toCol;j++){
				createCell(row, j);
			}
		}
	}
	
	/**
	 * 批量创建表格 同一个样式
	 * @param fromRow
	 * @param fromCol
	 * @param toRow
	 * @param toCol
	 * @param sheet
	 */
	public static void createCellsCommonStyle(HSSFCellStyle cellStyle,int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		//HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		for(int i=fromRow;i<=toRow;i++){
			HSSFRow row = getRow(i, sheet);
			for(int j=fromCol;j<=toCol;j++){
				HSSFCell cell = row.createCell(j);
				setBorder(cellStyle);
				cell.setCellStyle(cellStyle);
			}
		}
	}
	
	/**
	 * 设置有边框的样式
	 * @param cellStyle
	 * @return
	 */
	public static HSSFCellStyle setBorder(HSSFCellStyle cellStyle){
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	/**
	 * 设置加粗字体
	 * @param cellStyle
	 * @return
	 */
	public static HSSFCellStyle setFont(HSSFCellStyle cellStyle,HSSFSheet sheet){
		HSSFFont font = sheet.getWorkbook().createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		return cellStyle;
	}
	/**
	 * 设置打印属性
	 * @param sheet
	 */
	public static void setPrint(HSSFSheet sheet){
		//设置打印
		HSSFPrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setPaperSize(HSSFPrintSetup.A3_PAPERSIZE); // 纸张
		printSetup.setLandscape(true);//设置横向打印
		sheet.setMargin(HSSFSheet.TopMargin,(double)1);// 页边距（上）    
		sheet.setMargin(HSSFSheet.BottomMargin,(double)0);// 页边距（下）    
		sheet.setMargin(HSSFSheet.LeftMargin,(double)0);// 页边距（左）    
		sheet.setMargin(HSSFSheet.RightMargin,(double)0.4);// 页边距（右    
		sheet.setAutobreaks(true);//设置打印为一页  自动缩放
		
		sheet.setDisplayGridlines(false);//设置无网格
	}
	/**
	 * 给区域加粗外框 
	 */
	public static void setRegionBorderBolod(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		CellRangeAddress region = new CellRangeAddress(fromRow, toRow, fromCol, toCol);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THICK, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THICK, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THICK, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THICK, region, sheet, sheet.getWorkbook());
	}
	/**
	 * 给区域加粗外框 
	 */
	public static void setRegionBorderDB(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		CellRangeAddress region = new CellRangeAddress(fromRow, toRow, fromCol, toCol);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_DOUBLE, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_DOUBLE, region, sheet, sheet.getWorkbook());
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE, region, sheet, sheet.getWorkbook());
	}
	/**
	 * 给区域加粗外框 
	 */
	public static void setRegionBorderBolodBottom(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		CellRangeAddress region = new CellRangeAddress(fromRow, toRow, fromCol, toCol);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THICK, region, sheet, sheet.getWorkbook());
	}
	/**
	 * 设置边框
	 * @param cell
	 * @param wb
	 */
	public static HSSFCell setBorder(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置边框
	 * @param cell
	 * @param wb
	 */
	public static HSSFCell setBorderDotted(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 去除边框
	 * @param cell
	 * @return
	 */
	public static HSSFCell setBorderNone(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置加粗字体
	 */
	public static HSSFCell setFont(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置居中对齐
	 * @param cell
	 * @param wb
	 */
	public static HSSFCell setAlignCenter(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置靠左对齐
	 * @param cell
	 * @param wb
	 */
	public static HSSFCell setAlignLeft(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		return cell;
	}
	/**
	 * 设置靠右对齐
	 * @param cell
	 * @param wb
	 */
	public static HSSFCell setAlignRight(HSSFCell cell){
		HSSFWorkbook wb = cell.getRow().getSheet().getWorkbook();
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = wb.createCellStyle();
		}
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		return cell;
	}
	/**
	 * 设置黑色背景
	 * @return
	 */
	public static HSSFCell setBackgroundBlack(int r,int c,HSSFSheet sheet){
		HSSFRow row = HSSFCellUtil.getRow(r, sheet);
		HSSFCell cell = HSSFCellUtil.getCell(row, c);
		HSSFCellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
//		HSSFCellStyle cellStyle = cell.getCellStyle();
//		if(cellStyle == null){
//		}
		cellStyle.setFillBackgroundColor((short)HSSFColor.BLACK.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置黑色背景
	 * @param row
	 * @param c
	 * @return
	 */
	public static HSSFCell setBackgroundBlack(HSSFRow row,int c){
		HSSFCell cell = HSSFCellUtil.getCell(row, c);
		HSSFCellStyle cellStyle = cell.getCellStyle();
		if(cellStyle == null){
			cellStyle = row.getSheet().getWorkbook().createCellStyle();
		}
		cellStyle.setFillBackgroundColor((short)HSSFColor.BLACK.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	/**
	 * 设置黑色背景
	 * @param fromRow
	 * @param fromCol
	 * @param toRow
	 * @param toCol
	 * @param sheet
	 */
	public static void setBackgroundBlack(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		for(int i=fromRow;i<=toRow;i++){
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for(int j=fromCol;j<=toCol;j++){
				setBackgroundBlack(row, j);
			}
		}
	}
	/**
	 * 
	 * 设置区域边框
	 */
	public static void setRegionBorder(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		for(int r=fromRow;r <= toRow;r++){
			HSSFRow row = HSSFCellUtil.getRow(r, sheet);
			for(int c = fromCol;c<= toCol;c++){
				HSSFCell cell = HSSFCellUtil.getCell(row, (short)c);
				setBorder(cell);
			}
		}
	}
	/**
	 * 
	 * 设置区域边框
	 */
	public static void setRegionBorderDotted(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		for(int r=fromRow;r <= toRow;r++){
			HSSFRow row = HSSFCellUtil.getRow(r, sheet);
			for(int c = fromCol;c<= toCol;c++){
				HSSFCell cell = HSSFCellUtil.getCell(row, (short)c);
				setBorderDotted(cell);
			}
		}
	}
	/**
	 * 
	 * 去除边框
	 */
	public static void setRegionBorderNone(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		for(int r=fromRow;r <= toRow;r++){
			HSSFRow row = HSSFCellUtil.getRow(r, sheet);
			for(int c = fromCol;c<= toCol;c++){
				HSSFCell cell = HSSFCellUtil.getCell(row, (short)c);
				setBorderNone(cell);
			}
		}
	}
	/**
	 * 给区域设置加粗字体
	 */
	public static void setRegionFont(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		HSSFFont font = sheet.getWorkbook().createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		for(int r=fromRow;r<=toRow;r++){
			HSSFRow row = HSSFCellUtil.getRow(r, sheet);
			for(int c=fromCol;c<=toCol;c++){
				HSSFCell cell = HSSFCellUtil.getCell(row, (short)c);
				
				HSSFCellStyle cellStyle = cell.getCellStyle();
				if(cellStyle == null){
					cellStyle = sheet.getWorkbook().createCellStyle();
				}
				cellStyle.setFont(font);
				cell.setCellStyle(cellStyle);
			}
		}
	}
	/**
	 * 给区域设置样式
	 */
	public static void setRegionStyle(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet,HSSFCellStyle cellStyle){
		for(int r=fromRow;r<=toRow;r++){
			HSSFRow row = HSSFCellUtil.getRow(r, sheet);
			for(int c=fromCol;c<=toCol;c++){
				HSSFCell cell = HSSFCellUtil.getCell(row, (short)c);
				cell.setCellStyle(cellStyle);
			}
		}
	}
	/**
	 * 合并单元格
	 * @param fromRow
	 * @param fromCol
	 * @param toRow
	 * @param toCol
	 * @param sheet
	 */
	public static void combine(int fromRow,int fromCol,int toRow,int toCol,HSSFSheet sheet){
		sheet.addMergedRegion(new CellRangeAddress(fromRow, toRow, fromCol, toCol));
	}
	/**
	 * 设置值
	 * @param r
	 * @param c
	 * @param values
	 * @param sheet
	 */
	public static void setValues(int r,int c,String[] values,HSSFSheet sheet){
		HSSFRow row = HSSFCellUtil.getRow((Integer) r, sheet);
		for(int i=0;i<values.length;i++){
			HSSFCell cell = HSSFCellUtil.getCell(row, i + c);
			cell.setCellValue(values[i]);
		}
	}
	/**
	 * 设置值
	 * @param r
	 * @param c
	 * @param sheet
	 * @return
	 */
	public static HSSFCell setValue(int r,int c,String value,HSSFSheet sheet){
		HSSFRow row = HSSFCellUtil.getRow((Integer) r, sheet);
		HSSFCell cell = HSSFCellUtil.getCell(row, (Integer) c);
		cell.setCellValue(value);
		return cell;
	}
	//创建表格  
	public static HSSFWorkbook createExcel(ExcelConfig cf) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(cf.fileName);
		int col=0;//当前第几列
		int row=0;//当前第几行
		if(cf.title != null){//如果有title  添加title
			ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
			HSSFCell cell = ExcelUtil.setValue(row, col, cf.title, sheet);
			ExcelUtil.setFont(cell);
			ExcelUtil.setAlignCenter(cell);
			ExcelUtil.combine(row, col, row, col+cf.fieldNames.size()-1, sheet);
			row ++;
		}
		for(int i=0;i<cf.columnWidths.size();i++){
			sheet.setColumnWidth(col+i, cf.columnWidths.get(i));
		}
		//设置渲染函数
		if(cf.columnRenders == null){
			cf.columnRenders = new ArrayList<ColumnRender>();
			for(int i=0;i<cf.fieldDescs.size();i++){
				cf.columnRenders.add(ExcelConfig.getDefaultColumnRender());
			}
		}
		for(int i=0;i<cf.columnRenders.size();i++){
			if(cf.columnRenders.get(i) == null){
				cf.columnRenders.set(i, ExcelConfig.getDefaultColumnRender());
			}
		}
		//设置表头
		ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
		ExcelUtil.setValues(row, col, cf.fieldDescs.toArray(new String[]{}), sheet);
		ExcelUtil.setRegionFont(row, col, row, col+cf.fieldNames.size()-1, sheet);
		
		HSSFCellStyle commonCellStyle = wb.createCellStyle();
		
		row ++;
		//如果有分组
		if(cf.group){
			Map<String, List<Map<String, String>>> groupMap = new HashMap<String, List<Map<String,String>>>();
			for(Map<String, String> val : cf.values){
				String groupVal = val.get(cf.groupField);
				if(!groupMap.containsKey(groupVal)){
					groupMap.put(groupVal, new ArrayList<Map<String,String>>());
				}
				groupMap.get(groupVal).add(val);
				val.put("seq", String.valueOf(groupMap.get(groupVal).size()));
			}
			List<Entry<String, List<Map<String, String>>>> groupList =
					new ArrayList<Entry<String,List<Map<String,String>>>>(groupMap.entrySet());
			Comparator<Entry<String, List<Map<String, String>>>> groupComparator
				= cf.groupComparator == null ? new Comparator<Entry<String,List<Map<String,String>>>>() {
					@Override
					public int compare(Entry<String, List<Map<String, String>>> o1,
							Entry<String, List<Map<String, String>>> o2) {
						if(o1.getKey() == null){
							return -1;
						}
						return o1.getKey().compareTo(o2.getKey());
					}
				} : cf.groupComparator;
			Collections.sort(groupList,groupComparator);
			List<List<String>> summaryValues = null; 
			//汇总
			if(cf.summary){
				summaryValues = new ArrayList<List<String>>();
				for(int i=0;i<cf.summaryFields.size();i++){
					summaryValues.add(new ArrayList<String>());
				}
			}
			for(Entry<String, List<Map<String, String>>> en : groupList){
				ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
				ExcelUtil.setValues(row, col, new String[]{"-----"+en.getKey() + "(" + en.getValue().size() + ")"}, sheet);
				HSSFCell cell = ExcelUtil.getCell(row, col, sheet);
				ExcelUtil.setAlignLeft(cell);
				ExcelUtil.combine(row, col, row, col+cf.fieldNames.size()-1, sheet);
				ExcelUtil.setRegionFont(row, col, row, col+cf.fieldNames.size()-1, sheet);
				row ++;
				List<List<String>> groupValues = null;
				//如果有分组汇总
				if(cf.groupSummary){
					groupValues = new ArrayList<List<String>>();
					for(int i=0;i<cf.groupSummaryFields.size();i++){
						groupValues.add(new ArrayList<String>());
					}
				}
				//设置每一行
				for(Map<String, String> values : en.getValue()){
					ExcelUtil.createCellsCommonStyle(commonCellStyle,row, col, row, col+cf.fieldNames.size()-1, sheet);
					for(int i=0;i<cf.fieldNames.size();i++){
						String fieldName = cf.fieldNames.get(i);
						String val = values.get(fieldName);
						cell = ExcelUtil.setValue(row, col+i, cf.columnRenders.get(i).render(val), sheet);
						ExcelUtil.setAlignCenter(cell);
						//分组汇总
						if(cf.groupSummary && cf.groupSummaryFields.contains(fieldName)){
							groupValues.get(cf.groupSummaryFields.indexOf(fieldName)).add(val);
						}
						//大汇总
						if(cf.summary && cf.summaryFields.contains(fieldName)){
							summaryValues.get(cf.summaryFields.indexOf(fieldName)).add(val);
						}
					}
					row ++;
				}
				//分组汇总
				if(cf.groupSummary){
					ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
					cell = ExcelUtil.setValue(row, col, "小计:", sheet);
					ExcelUtil.setAlignCenter(cell);
					ExcelUtil.setRegionFont(row, col, row, col+cf.fieldNames.size()-1, sheet);
					for(int i=0;i<cf.groupSummaryFields.size();i++){
						SummaryRender summaryRender = null;
						if(cf.groupSummaryRenders != null && cf.groupSummaryRenders.get(i) != null){
							summaryRender = cf.groupSummaryRenders.get(i);
						}else{
							summaryRender = ExcelConfig.getDefaultSummaryRender();
						}
						cell = ExcelUtil.setValue(row, col+cf.fieldNames.indexOf(cf.groupSummaryFields.get(i)), summaryRender.render(groupValues.get(i)), sheet);
						ExcelUtil.setAlignCenter(cell);
					}
					row ++;
				}
			}
			if(cf.summary){
				ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
				HSSFCell cell = ExcelUtil.setValue(row, col, "总计:", sheet);
				ExcelUtil.setAlignCenter(cell);
				ExcelUtil.setRegionFont(row, col, row, col+cf.fieldNames.size()-1, sheet);
				for(int i=0;i<cf.summaryFields.size();i++){
					SummaryRender summaryRender = null;
					if(cf.summaryRenders != null && cf.summaryRenders.get(i) != null){
						summaryRender = cf.summaryRenders.get(i);
					}else{
						summaryRender = ExcelConfig.getDefaultSummaryRender();
					}
					cell = ExcelUtil.setValue(row, col+cf.fieldNames.indexOf(cf.summaryFields.get(i)), summaryRender.render(summaryValues.get(i)), sheet);
					ExcelUtil.setAlignCenter(cell);
				}
				row++;
			}
		}else{//没有分组
			List<List<String>> summaryValues = null; 
			//如果有汇总
			if(cf.summary){
				summaryValues = new ArrayList<List<String>>();
				for(int i=0;i<cf.summaryFields.size();i++){
					summaryValues.add(new ArrayList<String>());
				}
			}
			for(Map<String, String> values : cf.values){
				ExcelUtil.createCellsCommonStyle(commonCellStyle,row, col, row, col+cf.fieldNames.size()-1, sheet);
				for(int i=0;i<cf.fieldNames.size();i++){
					String fieldName = cf.fieldNames.get(i);
					String val = values.get(fieldName);
					HSSFCell cell = ExcelUtil.setValue(row, col+i, cf.columnRenders.get(i).render(val), sheet);
					ExcelUtil.setAlignCenter(cell);
					//大汇总
					if(cf.summary && cf.summaryFields.contains(fieldName)){
						summaryValues.get(cf.summaryFields.indexOf(fieldName)).add(val);
					}
				}
				row ++;
			}
			if(cf.summary){
				ExcelUtil.createCells(row, col, row, col+cf.fieldNames.size()-1, sheet);
				ExcelUtil.setRegionFont(row, col, row, col+cf.fieldNames.size()-1, sheet);
				HSSFCell cell = ExcelUtil.setValue(row, col, "总计:", sheet);
				ExcelUtil.setAlignCenter(cell);
				for(int i=0;i<cf.summaryFields.size();i++){
					SummaryRender summaryRender = null;
					if(cf.summaryRenders != null && cf.summaryRenders.get(i) != null){
						summaryRender = cf.summaryRenders.get(i);
					}else{
						summaryRender = ExcelConfig.getDefaultSummaryRender();
					}
					ExcelUtil.setValue(row, col+cf.fieldNames.indexOf(cf.summaryFields.get(i)), summaryRender.render(summaryValues.get(i)), sheet);
				}
				row ++;
			}
		}
		ExcelUtil.setRegionBorder(0, 0, row-1, col+cf.fieldNames.size()-1, sheet);
		ExcelUtil.setRegionBorderBolod(0, 0, row-1, col+cf.fieldNames.size()-1, sheet);
		
		if(cf.description != null){
			sheet = wb.createSheet("说明");
			HSSFCell cell = ExcelUtil.setValue(0, 0, cf.description, sheet);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			cell.setCellStyle(cellStyle);
			ExcelUtil.combine(0, 0, 30, 15, sheet);
		}
		
		if(cf.out != null){
			wb.write(cf.out);
		}
		return wb;
	}
	
	public static class ExcelConfig{
		public String title;//标题 可选项
		public String fileName;//文件名 必选项
		public List<String> fieldNames;//列名字  必选项
		public List<String> fieldDescs;//列描述  必选项
		public List<Integer> columnWidths;//列宽 必选项
		public List<ColumnRender> columnRenders;//列渲染 可选项
		public List<Map<String, String>> values;//属性值 必选项
		//public boolean seq=true;//默认需要序号 //可选项
		public boolean summary=false;//是否需要合计 //可选项
		public List<String> summaryFields;//合计字段 //可选项
		public boolean group=false;//是否需要分组 //可选项  当true时groupField为必选项
		public String groupField;//分组字段 //可选项
		public boolean groupSummary=false;//分组汇总 可选项 当true时groupSummaryFields为必选项
		public List<String> groupSummaryFields;//分组汇总字段 可选项
		public Comparator<Entry<String, List<Map<String, String>>>> groupComparator;//分组排序方法
		public List<SummaryRender> summaryRenders;//分组渲染函数  可选项
		public List<SummaryRender> groupSummaryRenders;//分组汇总
		public OutputStream out;//可选项  输出流
		
		public String description = null;//可选项  在sheet1做说明
		
		//默认的列渲染函数	  设计模式:享元模式,减少内存的使用
		private static ColumnRender defaultColumnRender;
		//默认的分组渲染函数     设计模式:享元模式,减少内存的使用
		private static SummaryRender defaultSummaryRender;
		
		public ExcelConfig(){}
		//获取默认的列渲染函数
		public static ColumnRender getDefaultColumnRender(){
			if(defaultColumnRender == null){
				defaultColumnRender = new ColumnRender() {
					@Override
					public String render(String value) {
						return value;
					}
				};
			}
			return defaultColumnRender;
		}
		//获取默认的分组渲染函数
		public static SummaryRender getDefaultSummaryRender(){
			if(defaultSummaryRender == null){
				defaultSummaryRender = new SummaryRender() {
					@Override
					public String render(List<String> values) {
						BigDecimal count = new BigDecimal(0);
						for(String val : values){
							count = count.add(new BigDecimal(Double.parseDouble(val)));
						}
						count = count.setScale(2, BigDecimal.ROUND_HALF_UP);
						return String.valueOf(count.doubleValue());
					}
				};
			}
			return defaultSummaryRender;
		}
	}
	//列渲染函数
	public static interface ColumnRender{
		public String render(String value);
	}
	//分组渲染函数
	public static interface SummaryRender{
		public String render(List<String> values);
	}
	/**
	 * 读取excel  从第一行 第0列开始读取   通常第0行是表头
	 * @param file  上传的文件
	 * @param fields 字段  按照顺序
	 * @return 返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(File file,String[] fields) throws IOException{
		return readExcel(file, 1, 0, fields);
	}
	
	/**
	 * 读取excel  从第一行 第0列开始读取   通常第0行是表头
	 * @param file  上传的文件
	 * @param fields 字段  按照顺序
	 * @return 返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(InputStream in,String[] fields) throws IOException{
		return readExcel(in, 1, 0, fields);
	}
	
	
	/**
	 * @param file   通过文件上传
	 * @param startRow 开始行
	 * @param startCol 开始列
	 * @param fields  字段 按照顺序
	 * @return  返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(File file,int startRow,int startCol,String[] fields) throws IOException{
		FileInputStream in = new FileInputStream(file);
		return readExcel(in, startRow, startCol, fields);
	}
	
	/**
	 * @param inputstream   通过文件上传
	 * @param startRow 开始行
	 * @param startCol 开始列
	 * @param fields  字段 按照顺序
	 * @return  返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(InputStream in,int startRow,int startCol,String[] fields) throws IOException{
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		for(int i=startRow;i<=lastRowNum;i++){
			HSSFRow row = sheet.getRow(i);
			short lastCellNum = row.getLastCellNum();
			
			Map<String, String> map = new HashMap<String, String>();
			
			for(int j=startCol;j<lastCellNum;j++){
				
				HSSFCell cell = row.getCell(j);
				//System.out.println(cell.toString());
				String value = null;
				if(cell != null){
					
					if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						DecimalFormat    df   = new DecimalFormat("######0.00");
						//value = StringUtil.removeLastZero((cell.getNumericCellValue()+""));
						value = StringUtil.removeLastZero(df.format(cell.getNumericCellValue()));
					}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
						value = cell.getStringCellValue();
					}else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
						value = "";
					}else{
						value = StringUtil.trim(cell.toString());
					}
				}
				map.put(fields[j-startCol], value);
				
			}
			list.add(map);
		}
		in.close();
		return list;
	}
	
	/**
	 * 2007/2010版的
	 * 读取excel  从第一行 第0列开始读取   通常第0行是表头
	 * @param file  上传的文件
	 * @param fields 字段  按照顺序
	 * @return 返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel07Or10(File file,String[] fields) throws IOException{
		return readExcel07Or10(file, 1, 0, fields);
	}
	
	/**
	 * 2007/2010版的
	 * 读取excel  从第一行 第0列开始读取   通常第0行是表头
	 * @param file  上传的文件
	 * @param fields 字段  按照顺序
	 * @return 返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel07Or10(InputStream in,String[] fields) throws IOException{
		return readExcel07Or10(in, 1, 0, fields);
	}
	
	/**
	 * 自动判断 03 和07/10 版的Excel
	 * @param file
	 * @param fields
	 * @param realFileName
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(File file,String[] fields,String realFileName) throws IOException{
		if(realFileName != null && realFileName.endsWith("xls")){
			//2003 Excel 
			return readExcel(file, fields);
		}else{
			//2007 或者 2010 Excel
			return readExcel07Or10(file, fields);
		}
	}
	
	/**
	 * 自动判断 03 和07/10 版的Excel
	 * @param file
	 * @param fields
	 * @param realFileName
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(InputStream in,String[] fields,String realFileName) throws IOException{
		if(realFileName != null && realFileName.endsWith("xls")){
			//2003 Excel 
			return readExcel(in, fields);
		}else{
			//2007 或者 2010 Excel
			return readExcel07Or10(in, fields);
		}
	}
	
	
	/**
	 * 自动判断 03 和07/10 版的Excel
	 * @param file
	 * @param startRow
	 * @param startCol
	 * @param fields
	 * @param realFileName
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(InputStream in,int startRow,int startCol,String[] fields,String realFileName) throws IOException{
		if(realFileName != null && realFileName.endsWith("xls")){
			//2003 Excel 
			return readExcel(in, startRow, startCol, fields);
		}else{
			//2007 或者 2010 Excel
			return readExcel07Or10(in, startRow, startCol, fields);
		}
	}
	
	
	/**
	 * 自动判断 03 和07/10 版的Excel
	 * @param file
	 * @param startRow
	 * @param startCol
	 * @param fields
	 * @param realFileName
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel(File file,int startRow,int startCol,String[] fields,String realFileName) throws IOException{
		if(realFileName != null && realFileName.endsWith("xls")){
			//2003 Excel 
			return readExcel(file, startRow, startCol, fields);
		}else{
			//2007 或者 2010 Excel
			return readExcel07Or10(file, startRow, startCol, fields);
		}
	}
	
	
	/**
	 * 07/10 版的Excel
	 * @param file   通过文件上传
	 * @param startRow 开始行
	 * @param startCol 开始列
	 * @param fields  字段 按照顺序
	 * @return  返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel07Or10(File file,int startRow,int startCol,String[] fields) throws IOException{
		FileInputStream in = new FileInputStream(file);
		return readExcel07Or10(in, startRow, startCol, fields);
	}
	
	
	/**
	 * 07/10 版的Excel
	 * @param file   通过文件上传
	 * @param startRow 开始行
	 * @param startCol 开始列
	 * @param fields  字段 按照顺序
	 * @return  返回一个  key为字段 value为单元格内容的 List<Map>集合
	 * @throws IOException
	 */
	public static List<Map<String, String>> readExcel07Or10(InputStream in,int startRow,int startCol,String[] fields) throws IOException{
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		XSSFWorkbook wb = new XSSFWorkbook(in);
		XSSFSheet sheet = wb.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		for(int i=startRow;i<=lastRowNum;i++){
			XSSFRow row = sheet.getRow(i);
			short lastCellNum = row.getLastCellNum();
			Map<String, String> map = new HashMap<String, String>();
			
			for(int j=startCol;j<lastCellNum;j++){
				
				XSSFCell cell = row.getCell(j);
				//System.out.println(cell.toString());
				String value = null;
				if(cell != null){
					value = StringUtil.trim(cell.toString());
				}
				
				map.put(fields[j-startCol], value);
				
			}
			
			list.add(map);
		}
		in.close();
		return list;
	}
}
