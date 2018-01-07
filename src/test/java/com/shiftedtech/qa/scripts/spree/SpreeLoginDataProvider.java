package com.shiftedtech.qa.scripts.spree;

import com.shiftedtech.qa.spree.utils.ExcelReader;
import com.tngtech.java.junit.dataprovider.DataProvider;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * Created by ShiftTeacher on 1/6/2018.
 */
public class SpreeLoginDataProvider {
    @DataProvider
    public static Object[][] loginDataProviderAsArray(){
        Object[][] data;

        data = new Object[][]{
                {"shiftqa01@gmail.com", "shiftedtech"} ,
                {"steveemmet@yahoo.com", "Pickles123"}
        };

        return data;
    }
    @DataProvider
    public static Object[][] loginDataProviderAsExcel(){
        Object[][] data = null;

        String dataFile = System.getProperty("user.dir") + "/src/test/resources/SpreeLoginData.xls";
        File dFile = new File(dataFile);
        if(dFile.exists()){
            try {
                Workbook workbook = Workbook.getWorkbook(dFile);
                Sheet sheet = workbook.getSheet("Sheet1");

                data = new Object[sheet.getRows() - 1][sheet.getColumns()];
                String cellData = null;
                for(int j = 0; j < sheet.getColumns(); j++){
                    for(int i = 1; i < sheet.getRows(); i++){
                        Cell cell = sheet.getCell(j,i);
                        CellType cellType = cell.getType();
                        if(cellType == CellType.LABEL){
                            cellData = cell.getContents();
                        }
                        else if(cellType == CellType.NUMBER){
                            cellData = cell.getContents().toString();
                        }
                        else {
                            System.out.println("Unknown cell type");
                        }
                        data[i - 1][j] = cellData;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }

        }

        return data;
    }

    @DataProvider
    public static Object[][] loginDataProviderAsExcelWithPOI(){
        Object[][] data = null;
        String dataFile = System.getProperty("user.dir") + "/src/test/resources/SpreeLoginData.xls";

        ExcelReader reader = new ExcelReader(dataFile);
        data = reader.getExcelSheetData("Sheet1",true);


        return data;
    }
}
