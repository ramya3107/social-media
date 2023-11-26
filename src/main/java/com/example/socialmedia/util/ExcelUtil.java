package com.example.socialmedia.util;

import com.example.socialmedia.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
  public static String HEADER[]={"id","name","dob"};
  public static String SHEET_NAME="sheetForUserData";

  public static ByteArrayInputStream dataToExcel(List<User> userList) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
    try {
    Sheet sheet=workbook.createSheet(SHEET_NAME);
    Row row = sheet.createRow(0);
    for(int i=0;i< HEADER.length;i++){
      Cell cell= row.createCell(i);
      cell.setCellValue(HEADER[i]);
    }
    int rowIndex=1;
    for(User u:userList){
      Row row1=sheet.createRow(rowIndex);
      rowIndex++;
      row1.createCell(0).setCellValue(u.getId());
      row1.createCell(1).setCellValue(u.getName());
      row1.createCell(2).setCellValue(u.getDob());
    }

      workbook.write(byteArrayOutputStream);
      return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      workbook.close();
      byteArrayOutputStream.close();
    }
  }
}
