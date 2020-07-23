package Helper.Excel;

// Need to read data per col name
// Need to populate all data at once
// Need to define the -ve and +ve data


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    private static File excelFile;
    private static FileInputStream fis;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    public static List<DataSetModel> dataSet = new ArrayList<DataSetModel>();

    public static void LoadExcelFile(String fileName, String sheetName){
        try
        {
            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fileName);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);
            fis.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void populateExcelDataToDataSetModel(String fileName, String sheetName){
        clearDataSetModel();
        LoadExcelFile(fileName, sheetName);
        Row headerRow = getRowHeader();
        int rowNo = getRowNumber();
        int col = getColumnNumber();
        for (int i = 1; i < rowNo; i++) {
            Row row = getWholeRow(i);
            for (int j = 0; j < col; j++) {
                // Get the column name
                DataSetModel setModel = new DataSetModel();
                setModel.rowNumber = i;
                setModel.columnName = headerRow.getCell(j).getStringCellValue().trim();
                setModel.columnValue = row.getCell(j).getStringCellValue().trim();

                dataSet.add(setModel);
            }
        }
    }

    public static String readData(int rowNo, String colName ){
        int row = rowNo - 1;
        String value = null;
        for (int i = 0; i < dataSet.size() ; i++) {
            if (dataSet.get(i).getColumnName().equals(colName)){
                if(dataSet.get(i).getRowNumber() == row )
                {
                    value = dataSet.get(i).getColumnValue();
                }
            }
        }
        return value;
    }

    public static Row getRowHeader(){
        return sheet.getRow(0);
    }
    public  static Row getWholeRow(int rowNo){
        return sheet.getRow(rowNo);
    }

    public static int getRowNumber(){
        return sheet.getPhysicalNumberOfRows();
    }
    public static int getColumnNumber(){
        return row.getPhysicalNumberOfCells();
    }

    public static void clearDataSetModel(){
        dataSet.clear();
    }
}
