package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.LoginService;
import com.example.bno2.service.OtpService;
import com.example.bno2.utils.ImageToBlob;
import com.example.bno2.utils.OTPUtil;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@Tag(name = "OTP")
@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    public ImageToBlob imageToBlob = new ImageToBlob();
    public OTPUtil otpUtil = new OTPUtil();

    @Autowired
    private LoginService loginService;

    @Operation(summary = "OTP 등록")
    @GetMapping("/OTPRegist")
    @ResponseBody
    public ResponseEntity<byte[]> otpRegist(HttpSession session) throws IOException, WriterException {

        User loginUser = (User)session.getAttribute("loginUser");
        int userPn = loginUser.getPn();

        if(otpService.checkQrBlob(userPn) == 0){

            if(otpService.checkSecretKey(userPn) == 0){
                otpService.addSecretKey(userPn, otpUtil.getSecretKey());
            }

            String filePath = System.getProperty("user.home") + "/Desktop/image";
            File folder = new File(filePath);

            if(!folder.exists()){
                folder.mkdirs();
            }

            if(!folder.canWrite()){
                folder.setWritable(true);
            }

            otpUtil.getQRImage(otpUtil.getGoogleOTPAuthURL(otpService.getSecretKey(userPn), loginUser.getEmail(), "bnosoft"),filePath+"/" + userPn +"_QR.png", 900, 900);
            byte[] blob = imageToBlob.convertImageToBlob(filePath+"/" + userPn +"_QR.png");
            otpService.addQrBlob(userPn,blob);

        }

        String url = "jdbc:mysql://124.63.21.91:5555/bnodb?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "admin";
        String dbPassword = "admin";
        String sql = "SELECT qr_blob FROM otp_regist WHERE user_pn = ?";

        try (Connection conn = DriverManager.getConnection(url, username, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // user_pn에 해당하는 Blob을 가져오도록 설정
            pstmt.setInt(1, userPn);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Blob 가져오기
                    Blob blob = rs.getBlob("qr_blob");

                    // Blob을 InputStream으로 변환
                    InputStream inputStream = blob.getBinaryStream();

                    byte[] imgageBytes = inputStream.readAllBytes();

                    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imgageBytes);

                    // InputStream을 사용하여 Blob 데이터 처리
                    // 여기서는 HttpServletResponse를 사용하여 직접 클라이언트에게 전송하는 예제를 보여줍니다.
//                        response.setContentType("image/jpeg");
//                        OutputStream outputStream = response.getOutputStream();
//                        byte[] buffer = new byte[1024];
//                        int bytesRead;
//                        while ((bytesRead = inputStream.read(buffer)) != -1) {
//                            outputStream.write(buffer, 0, bytesRead);
//                        }
//                        outputStream.flush();
                } else {
                    // Blob이 없는 경우 처리
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Operation(summary = "OTP 인증")
    @GetMapping("/OTPAuth")
    @ResponseBody
    public ResponseEntity<String> googleOTPAuth(@RequestParam String otp, HttpSession session){

        User loginUser = (User)session.getAttribute("loginUser");
        int userPn = loginUser.getPn();

        String otpKey = otpUtil.getTOTPCode(otpService.getSecretKey(userPn));

        if(otpKey.equals(otp)){
            loginService.addLoginHistory(userPn);

            if(otpService.otpIsRegistered(userPn) == 0){
                otpService.updateOtpIsRegisterd(userPn);
                return new ResponseEntity<>("OTP Register Success, Login Success.", HttpStatus.OK);
            }

            return new ResponseEntity<>("OTP Auth Success, Login Success.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("OTP Auth fail.", HttpStatus.OK);
        }

    }

}