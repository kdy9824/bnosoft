package com.example.bno2.controller;

import com.example.bno2.dto.Excel;
import com.example.bno2.service.ExcelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Tag(name="엑셀")
@Controller 
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Operation(summary = "엑셀 다운로드")
    @GetMapping("/excelDownload")
    public List<Excel> downloadUserInfo(HttpServletResponse response) throws IOException {

        // 엑셀 파일 하나를 만듭니다
        Workbook workbook = new SXSSFWorkbook();

        // 엑셀 파일 내부에 sheet를 하나 생성합니다
        Sheet sheet = workbook.createSheet();

        // 엑셀 렌더링에 필요한 DTO을 가져와야한다
        List<Excel> excelDtos = excelService.getExcelInfo();

        String[] headers = {"index", "projectUid", "projectName", "startDt", "endDt", "startPredictDt", "endPredictDt", "projectStateCode", "applyYn", "projectDetail"};

        // 헤더를 생성합니다
        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);

        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headers[i]);
        }

        // 바디에 데이터를 넣어줍니다
        for (Excel dto : excelDtos) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < headers.length; i++) {
                try {
                    // Dto 클래스에서 headers[i] 이름을 가진 필드를 찾습니다
                    Field field = dto.getClass().getDeclaredField(headers[i]);
                    // private 필드에 접근할 수 있도록 설정
                    field.setAccessible(true);
                    // 필드 값을 가져옴
                    Object value = field.get(dto);
                    row.createCell(i).setCellValue((value != null ? value.toString() : ""));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // 예외를 여기서 처리합니다. 로그를 남기거나 적절히 처리합니다.
                    e.printStackTrace();
                }
            }
        }
        // 응답 설정 및 엑셀 파일 전송
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=project_info.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
//        return excelDtos;
    }
}

