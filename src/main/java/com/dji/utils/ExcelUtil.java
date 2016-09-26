package com.dji.utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


/**
 * 将元素放在excel统一管理，要获取元素的信息，首先从excel读取。
 * 读取excel的页面元素是使用POI开源框架
 * 
 * @author Charlie.chen
 *
 */

public class ExcelUtil {

	/**
	 * @author Charlie.chen
	 * @return
	 * @throws IOException
	 */
	public static String[][] getLocatorMap(String path) throws IOException {
		
		FileInputStream in = new FileInputStream(path);
		
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(in));
		
		Sheet sheet = wb.getSheetAt(0);
		
		//行数
		int rowNums = sheet.getLastRowNum(); 	
		Row header = sheet.getRow(0);
		//列数
		int cellNums=header.getLastCellNum(); 
		
		String[][] locatorMap = new String[rowNums+ 1][cellNums];
		
		for (int rowNum = 0; rowNum <= rowNums; rowNum++) {
			// for (Cell cell : row)
			Row row = sheet.getRow(rowNum);

			if (row == null) {
				continue;
			}
			
			String value;
			
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
				
				Cell cell = row.getCell(cellNum);
				
				if (cell == null) {
					continue;
				} else {
					value = "";
				}
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						value = cell.getDateCellValue().toString();

					} else {
						value = Double.toString((int) cell.getNumericCellValue());

					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = Boolean.toString(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = cell.getCellFormula().toLowerCase();
					break;
				default:
					value = " ";
					System.out.println();
				}
				locatorMap[rowNum][cellNum] = value;
			}
		}
		in.close();
		wb.close();

		return locatorMap;
	}
	
	
	
	//测试代码
/*	public static void main(String[] args) throws IOException {
		String[][] locatorMap=ReadExcelUtil.getLocatorMap(path);
		
		for(int i=0;i<locatorMap.length;i++){
			for(int j=0;j<locatorMap[i].length;j++){
				System.out.println(locatorMap[i][j]);
			}
			System.out.println("------------------");
		}

	}*/

}
