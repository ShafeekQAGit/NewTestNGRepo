import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;

public class dataProviderDemo {
    public static Object[][] readExcelDataProvider(String excelPath,String sheetName){
        ReadExcel readExcel=new ReadExcel();
        XSSFSheet sheet = readExcel.getExcelSheet(excelPath,sheetName);
        int rowCount = sheet.getLastRowNum();
        int column = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][column];
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < column; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(cell);
                data[i - 1][j] = val;
            }
        }

        return data;
    }
    @DataProvider(name="Login")
    public static Object[][] loginDataProvider(){
        return dataProviderDemo.readExcelDataProvider("./excel/Testdata.xlsx", "Login");
    }

}


