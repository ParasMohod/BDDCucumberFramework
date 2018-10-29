package utilities;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import suiteSetup.inputForms.Setup_ObjectRepository;;

public class ExcelData 
{	
	private String filePath;
	private String wrkSheetName;
	private String TestColName;
	public int key=0;
	
	public HashMap<String, String> getData(String testname, String repositoryPath) throws Exception
	{
		Setup_ObjectRepository obj = new Setup_ObjectRepository(repositoryPath);
		
		//obj.setVariables(repositoryPath);
		filePath = obj.ExcelPath;
		wrkSheetName = obj.workbookSheetName;
		TestColName = obj.TestCaseColumnName;
		
		HashMap<String, String> data = new HashMap<String, String>();
		ArrayList<String> ColNames = new ArrayList<String>();
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheetNo = workbook.getNumberOfSheets();
		for(int i=0; i<sheetNo; i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(wrkSheetName))
			{
				XSSFSheet sheetName = workbook.getSheetAt(i);
				//Identifying testcases column
				Iterator<Row> rows = sheetName.iterator();
				Row firstRow = rows.next();
				
				Iterator<Cell> cell = firstRow.cellIterator();
				int columnCount=0;
				int columnNo = 0;
				while(cell.hasNext())
				{
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(TestColName))
					{
						columnNo=columnCount;
					}
					//To get Column names in arraylist
					if(value.getCellTypeEnum()==CellType.STRING)
					{
						ColNames.add(columnCount, value.getStringCellValue());
					}
					else
					{
						String cellData = NumberToTextConverter.toText(value.getNumericCellValue());
						ColNames.add(columnCount, cellData);
					}
					columnCount++;
				}
				while(rows.hasNext())
				{
					Row r = rows.next();
					if(r.getCell(columnNo).getStringCellValue().equalsIgnoreCase(testname))
					{
						Iterator<Cell> cellN = r.cellIterator();
						while(cellN.hasNext())
						{
							try
							{
								Cell cellValue = cellN.next();
								if(cellValue.getCellTypeEnum()==CellType.STRING)
								{
									data.put(ColNames.get(key), cellValue.getStringCellValue());
								}
								else
								{
									String cellData = NumberToTextConverter.toText(cellValue.getNumericCellValue());
									data.put(ColNames.get(key), cellData);
								}
								key++;
							}
							catch(Exception e)
							{
								break;
							}
						}
					}
				}
			}
		}
		System.out.println(data);
	return data;
  }
}
