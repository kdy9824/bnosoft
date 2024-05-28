//package com.example.bno2.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@PostMapping("/excel")
//public class ExcelController (@RequestBody List<Excel> excels, HttpServeletResponse response) {
//
//    workbook wb = new XSSFWorkbook();
//    Sheet sheet = wb.createSheet("첫번째 시트");
//    Row row;
//    int rowNum = 0;
//
//    row = sheet.createRow(rowNum++);
//    String[] headers = {"Index", "Project UID", "Project Name", "Start Date", "End Date", "Start Predict Date", "End Predict Date", "Project State Code", "Apply Yn", "Project Detail"};
//    for(int i=0; i<headers.length; i++){
//        row.createCell(i).setCellValue(headers[i]);
//    }
//
//}