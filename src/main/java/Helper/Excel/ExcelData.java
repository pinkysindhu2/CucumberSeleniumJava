package Helper.Excel;


import java.util.List;

/*
 *       Usage for this class:
 *       1. Set the Data source(which excel file you want to use) -> ExcelUtil.setDataSource("credential.xlsx");
 *       2. Set the Sheet you want to get data from -> ExcelUtil.dataSet.selectSheet("ANY")
 *       3. Get the row data by key(the key column in the excel sheet)
 *       4. var rowData = ExcelUtil.dataSet.selectSheet("ANY").getRowByKey("ANY");
 *       5. You can access the rowData by providing column name -> rowData.getValue("ANY")
 */
public class ExcelData {
    protected List<ExcelModel> data;
    protected List<DataSetModel> dataSet;

    public List<ExcelModel> getData() {
        return data;
    }

    public List<DataSetModel> getDataSet() {
        return dataSet;
    }

    public void setData(List<ExcelModel> data) {
        this.data = data;
    }

    public void setDataSet(List<DataSetModel> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * You should call this method before using any function in this class
     * @param sheet Name of the excel sheet name
     * @return Return ExcelData and ready for the method chaining
     */
    public ExcelData SelectSheet(String sheet)
    {
        var i = data.iterator();
        //x => x.Sheet == sheet).ToList();
        return this;
    }
}
