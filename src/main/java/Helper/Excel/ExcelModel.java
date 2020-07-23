package Helper.Excel;

import java.util.List;

public class ExcelModel {
    public List<DataSetModel> dataSet;
    public String key;
    public String sheet;
    public String excelFileName;

    public List<DataSetModel> getDataSet() {
        return dataSet;
    }

    public String getKey() {
        return key;
    }

    public String getSheet() {
        return sheet;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setDataSet(List<DataSetModel> dataSet) {
        this.dataSet = dataSet;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}

class DataSetModel
{
    public int rowNumber;
    public String columnName;
    public String columnValue;

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getColumnName(){
        return columnName;
    }

    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
