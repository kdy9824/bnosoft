package com.example.bno2.service;

import com.example.bno2.dto.Excel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExcelService {

    List <Excel> getExcelInfo();

    List<Excel> downloadUserInfo(HttpSession response);
}
