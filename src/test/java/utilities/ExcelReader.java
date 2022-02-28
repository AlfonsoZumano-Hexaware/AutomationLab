package utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static Map<String, String> getMapData() throws IOException{
		
		Map<String, String> testData = new HashMap<String, String>();
		DataFormatter formatter = new DataFormatter();
		
		try {
			//Read file
			FileInputStream fileInputStream = new FileInputStream("./dataFiles/testData.xlsx");
			
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			
			//Get the first sheet
			Sheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			
			//Iterate thru the rows
			for(int i = 0; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				
				//Get the keys located in the first column
				Cell keyCell = row.getCell(0);
				String key = formatter.formatCellValue(keyCell);
				
				//Get the values located in the second column
				Cell valueCell =  row.getCell(1);
				String value = formatter.formatCellValue(valueCell);
				
				//Put the keys and values in a hash map
				testData.put(key, value);
				workbook.close();
				
			}
				
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return testData;
	}
	
	//Read the map and return the data based on the key selected
	public String readData(String dataKey) throws IOException {
			Map<String, String> testData = ExcelReader.getMapData();
			return testData.get(dataKey);
	}
}
