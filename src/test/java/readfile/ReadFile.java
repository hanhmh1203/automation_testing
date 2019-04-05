package readfile;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.tools.ant.taskdefs.Sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadFile {
//    public static final String SAMPLE_XLSX_FILE_PATH = "/Users/hanhmh1203/Downloads/excelfile/assign_location1.xlsx";
    public static final String PUT_TRAILER_AT_OFFICE_FIRSTS = "/Users/hanhmh1203/Downloads/excelfile/put_trailer_at_office_firsts.xlsx";
    public static final String PUT_TRAILER_AT_DISCHARGING_FIRST = "/Users/hanhmh1203/Downloads/excelfile/put_trailer_at_discharging_first.xlsx";
    public static final String LOADING2_1DISCHARGE_PUT_TRAILER_AT_LOADING_FIRST = "/Users/hanhmh1203/Downloads/excelfile/2_loading_1_discharge_Put_trailer_at_loading_first..xlsx";
    public static final String SAMPLE_XLSX_FILE_PATH = PUT_TRAILER_AT_OFFICE_FIRSTS;

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

    private static List<LatLonEntity> listLocation;

    public static List<LatLonEntity> getListLocation() {
        if (listLocation == null || listLocation.size() == 0) {
            listLocation = getListLocationFromFile();
        }
        return listLocation;
    }

    private static List<LatLonEntity> getListLocationFromFile() {
        List<LatLonEntity> locations = new ArrayList<LatLonEntity>();

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
        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cellLat = row.getCell(5);
            Cell cellLon = row.getCell(6);

            String cellValueLat = dataFormatter.formatCellValue(cellLat);
            String cellValueLon = dataFormatter.formatCellValue(cellLon);
            int trackingTime =0;
            if(i==sheet.getLastRowNum()){
                trackingTime=0;
            }else{
                Row nextRow = sheet.getRow(i+1);
                Cell cellTime = nextRow.getCell(7);
                String strTrackingTime = dataFormatter.formatCellValue(cellTime);
                trackingTime=getSecondeByString(strTrackingTime);
            }
            LatLonEntity entity = new LatLonEntity(cellValueLat, cellValueLon, trackingTime);
            System.out.println("latlon entity" + entity.toString());
            locations.add(entity);
        }
        return locations;
    }

    private static int getSecondeByString(String time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        int seconds = 0;
        Date reference = null;
        try {
            reference = dateFormat.parse("00:00:00");
            Date date = dateFormat.parse(time);
            seconds = (int)((date.getTime() - reference.getTime()) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return seconds;
    }
}
