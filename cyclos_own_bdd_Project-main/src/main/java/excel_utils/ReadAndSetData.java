package excel_utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndSetData {
	
	XSSFWorkbook wb;
	XSSFSheet s;
	
	public ReadAndSetData(String excel,String sheet) throws Exception
	{
		FileInputStream fi=new FileInputStream(excel);
		wb=new XSSFWorkbook(fi);
		s=wb.getSheet(sheet);
	}
	
	//get last row number
	public int getRows()
	{
		return s.getLastRowNum();
	}
	
	//Get data from sheet
	public String getData(int row,int column)
	{
		return s.getRow(row).getCell(column).getStringCellValue();
	}
	
	//set data in to sheet
	public void setData(int row,int column,String value,String excel) throws Exception
	{
		//File out put stream
		FileOutputStream fos=new FileOutputStream(excel);
		
		//get row and columns and set value
		s.getRow(row).createCell(column).setCellValue(value);
		
		//save data
		wb.write(fos);
		
	}

}
