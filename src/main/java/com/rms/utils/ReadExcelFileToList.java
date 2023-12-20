package com.rms.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.rms.entity.RmsRuleEntity;
import com.rms.exception.CommonsException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadExcelFileToList {
    private static Workbook validateExcel(InputStream fis, String fileName) {
        try {
            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = null;
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new CommonsException("Invalid file type to upload");
            }
            return workbook;
        } catch (IOException e) {
            throw new CommonsException("Invalid file type to upload");
        }
    }

    public static Set<RmsRuleEntity> readExcelData(MultipartFile file, Date applicableDate) {
        Set<RmsRuleEntity> ruleEntityList = new HashSet<>();
        try {
            //Create the input stream from the xlsx/xls file
            InputStream fis = file.getInputStream();
            Workbook workbook = validateExcel(fis, file.getOriginalFilename());
            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();
            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    String doctorType = "";
                    String treatmentType = "";
                    String benefitType = "";
                    String benefitValueHeader = "";
                    Double benefitValue = 0.0;
                    //Get the row object
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    if(row.getRowNum()==0){
                        while (cellIterator.hasNext()) {
                            //Get the Cell object
                            Cell cell = cellIterator.next();
                            //check the cell type and process accordingly
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    if (doctorType.equalsIgnoreCase("")) {
                                        doctorType = cell.getStringCellValue().trim();
                                    } else if (treatmentType.equalsIgnoreCase("")) {
                                        //2nd column
                                        treatmentType = cell.getStringCellValue().trim();
                                    } else if (benefitType.equalsIgnoreCase("")) {
                                        //2nd column
                                        benefitType = cell.getStringCellValue().trim();
                                    } else if (benefitValueHeader.equalsIgnoreCase("")) {
                                        benefitValueHeader = cell.getStringCellValue().trim();
                                    } else {
                                        //random data, leave it
                                        System.out.println("Random data::" + cell.getStringCellValue());
                                    }
                            }
                        } //end of cell iterator
                        if(!doctorType.equalsIgnoreCase("doctorType") ||
                                !treatmentType.equalsIgnoreCase("treatmentType")||
                                !benefitType.equalsIgnoreCase("benefitType") ||
                                !benefitValueHeader.equalsIgnoreCase("benefitValue"))
                            throw new CommonsException("Invalid file format, please correct and reload");
                        continue; //just skip the rows if row number is 0 or 1
                    }
                    //Every row has columns, get the column iterator and iterate over them
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        //check the cell type and process accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (doctorType.equalsIgnoreCase("")) {
                                    doctorType = cell.getStringCellValue().trim();
                                } else if (treatmentType.equalsIgnoreCase("")) {
                                    //2nd column
                                    treatmentType = cell.getStringCellValue().trim();
                                } else if (benefitType.equalsIgnoreCase("")) {
                                    //2nd column
                                    benefitType = cell.getStringCellValue().trim();
                                } else {
                                    //random data, leave it
                                    System.out.println("Random data::" + cell.getStringCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (benefitValue == 0.0) {
                                    benefitValue = cell.getNumericCellValue();
                                }
                        }
                    } //end of cell iterator
                    RmsRuleEntity c = new RmsRuleEntity(doctorType, treatmentType, benefitType, benefitValue, applicableDate);
                    ruleEntityList.add(c);
                } //end of rows iterator
            } //end of sheets for loop
            //close file input stream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleEntityList;
    }
}



