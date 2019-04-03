package readfile;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.tools.ant.taskdefs.Sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadFile {
    public static final String SAMPLE_XLSX_FILE_PATH = "/Users/hanhmh1203/Downloads/excelfile/assign_location.xlsx";

    public static void main(String[] args) {
//        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
//        // Retrieving the number of sheets in the Workbook
//        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
//        // 2. Or you can use a for-each loop
//        System.out.println("Retrieving Sheets using for-each loop");
//        for (Sheet sheet : workbook) {
//            System.out.println("=> " + sheet.getSheetName());
//        }
//        // Getting the Sheet at index zero
//        Sheet sheet = workbook.getSheetAt(0);
//        // Create a DataFormatter to format and get each cell's value as String
//        DataFormatter dataFormatter = new DataFormatter();
//
//        // 1. You can obtain a rowIterator and columnIterator and iterate over them
//        System.out.println("last row number " + sheet.getLastRowNum());
//        for(int i=3;i<sheet.getLastRowNum();i++){
//            Row row = sheet.getRow(i);
//            Cell cellLat = row.getCell(5);
//            Cell cellLon = row.getCell(6);
//            String cellValueLat = dataFormatter.formatCellValue(cellLat);
//            String cellValueLon = dataFormatter.formatCellValue(cellLon);
//            System.out.print(cellValueLat + "\t" + cellValueLon);
//            System.out.println();
//        }
//        for (Row row : sheet) {
////            for(int i =0;i<row.getce)
////            for(Cell cell: row) {
////                String cellValue = dataFormatter.formatCellValue(cell);
////                System.out.print(cellValue + "\t");
////            }
//            Cell cellLat = row.getCell(5);
//            Cell cellLon = row.getCell(6);
//            String cellValueLat = dataFormatter.formatCellValue(cellLat);
//            String cellValueLon = dataFormatter.formatCellValue(cellLon);
//            System.out.print(cellValueLat + "\t" + cellValueLon);
//            System.out.println();
//        }
        // Closing the workbook
//        workbook.close();
//        ReadFile readFile = new ReadFile();
//        readFile.getListLocation();
        getListLocation();
    }
    private static List<LatLonEntity>listLocation;
    public static List<LatLonEntity> getListLocation(){
        if(listLocation==null||listLocation.size()==0){
            listLocation = getListLocationFromFile();
        }
        return listLocation;
    }
    private static List<LatLonEntity> getListLocationFromFile()  {
        List<LatLonEntity>locations = new ArrayList<LatLonEntity>();

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        for (Sheet sheet : workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("last row number " + sheet.getLastRowNum());
        for(int i=3;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Cell cellLat = row.getCell(5);
            Cell cellLon = row.getCell(6);
            String cellValueLat = dataFormatter.formatCellValue(cellLat);
            String cellValueLon = dataFormatter.formatCellValue(cellLon);
            LatLonEntity entity = new LatLonEntity(cellValueLat,cellValueLon);
            locations.add(entity);

            System.out.print(cellValueLat + "\t" + cellValueLon);
            System.out.println();
        }
        return  locations;
    }
}
