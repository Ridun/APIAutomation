package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadfromExcel {
	
	public void readExcel(String filePath,String fileName,String sheetName) throws IOException {
		
		//Create an object of FileInputStream class to read excel file
		File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    
	     //XSSFWorkbook wb = null;
	    
	    XSSFWorkbook wb = new XSSFWorkbook(inputStream);
	  //Read sheet inside the workbook by its name
	    XSSFSheet s = wb.getSheet(sheetName);
	    
	  //Find number of rows in excel file
	    
	    int rowcount = s.getLastRowNum()-s.getFirstRowNum();
	    
	    //Create a loop over all the rows of excel file to read it
	    
	    for (int i = 0; i < rowcount+1; i++) {
	    	
	    	XSSFRow row = s.getRow(i);
	    	
	    //Create a loop to print cell values in a row
	    	for (int j = 0; j < row.getLastCellNum(); j++) {

	            //Print Excel data in console
	    		
	    		System.out.print(row.getCell(j).getStringCellValue()+"|| ");

	        }

	        System.out.println();
	       // wb.close();
	        
	    } 
	    }

	

	public static void main(String[] args) throws IOException {
		
		ReadfromExcel objExcelfile = new ReadfromExcel();
		
		//Prepare the path of excel file
		
		String filePath = System.getProperty("user.dir")+"\\src\\practice";
		//Call read file method of the class to read data
		objExcelfile.readExcel(filePath, "poiRead.xlsx","Details");
		
	
	}

}
