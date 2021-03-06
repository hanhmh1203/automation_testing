package readfile;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.tools.ant.taskdefs.Patch;
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
    //        public static final String SAMPLE_XLSX_FILE_PATH1 = "/Users/hanhmh1203/Downloads/excelfile_clone/2loading_1dis_dis_first.xlsx";
    private static final String PATH = "/Users/hanhmh1203/Downloads/excelfile_clone/";
    public static final String PUT_TRAILER_AT_OFFICE_FIRSTS = PATH + "put_trailer_at_office_firsts.xlsx";
    public static final String PUT_TRAILER_AT_DISCHARGING_FIRST = PATH + "put_trailer_at_discharging_first.xlsx";
    public static final String LOADING2_1DISCHARGE_PUT_TRAILER_AT_LOADING_FIRST = PATH + "2_loading_1_discharge_Put_trailer_at_loading_first.xlsx";
    public static final String LOADING2_1DISCHARGE_PUT_TRAILER_AT_OFFICE_FIRST = PATH + "2_loading_1_discharge_Put_trailer_at_office_first.xlsx";
    public static final String LOADING2_1DISCHARGE_PUT_TRAILER_AT_DISCHARGE_FIRST = PATH + "2_loading_1discharge_trailer_at_discharge_first.xlsx";
    public static final String LOADING1_2DISCHARGE_PUT_TRAILER_AT_LOADING_FIRST = PATH + "1loading_2dischar_trailer_att_loading_first.xlsx";
    public static final String LOADING1_2DISCHARGE_PUT_TRAILER_AT_OFFICE_FIRST = PATH + "1loading_2dischar_trailer_att_office_first.xlsx";
    public static final String LOADING1_2DISCHARGE_PUT_TRAILER_AT_DISCHARGE_FIRST = PATH + "1loading_2dischar_trailer_att_discharge_first.xlsx";
    //
    public static final String SAMPLE_XLSX_FILE_PATH = LOADING1_2DISCHARGE_PUT_TRAILER_AT_DISCHARGE_FIRST;

    //2_loading_1discharge_trailer_at_discharge_first
    public static void main(String[] args) {
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
            int trackingTime = 0;
            if (i == sheet.getLastRowNum()) {
                trackingTime = 0;
            } else {
                Row nextRow = sheet.getRow(i + 1);
                Cell cellTime = nextRow.getCell(7);
                String strTrackingTime = dataFormatter.formatCellValue(cellTime);
                trackingTime = getSecondeByString(strTrackingTime);
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
            seconds = (int) ((date.getTime() - reference.getTime()) / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return seconds;
    }
}
