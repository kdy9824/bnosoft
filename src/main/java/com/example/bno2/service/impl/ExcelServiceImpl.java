package com.example.bno2.service.impl;

import com.example.bno2.dto.Excel;
import com.example.bno2.service.ExcelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public List<Excel> getExcelInfo() {
        // 데이터를 반환, 데이터베이스에서 가져오도록 구현

        List<Excel> list = new ArrayList<>();
        list.add(new Excel(1, "UID001", "Project 1", "2023-01-01", "2023-12-31", "2023-01-01", "2023-12-31", "Active", "Y", "Detail 1"));
        return list;
    }

    @Override
    public List<Excel> downloadUserInfo(HttpSession response){
        // 실제 엑셀 다운로드 로직이 Controller에서 실행
        return null;
    }
}
