package com.example.bno2.service.impl;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.LoginMapper;
import com.example.bno2.mapper.OtpMapper;
import com.example.bno2.service.OtpService;
import com.example.bno2.utils.ImageToBlob;
import com.example.bno2.utils.OTPUtil;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpMapper otpMapper;

    @Autowired
    LoginMapper loginMapper;

    public ImageToBlob imageToBlob = new ImageToBlob();
    public OTPUtil otpUtil = new OTPUtil();

    @Override
    public ResponseEntity<byte[]> otpRegister(HttpSession session) throws IOException, WriterException {

        User loginUser = (User)session.getAttribute("loginUser");
        int userPn = loginUser.getPn();

        if(otpMapper.checkQrBlob(userPn) == 0){

            if(otpMapper.checkSecretKey(userPn) == 0)
                otpMapper.addSecretKey(userPn, otpUtil.getSecretKey());

            String filePath = System.getProperty("user.home") + "/Desktop/image";
            File folder = new File(filePath);

            if(!folder.exists())
                folder.mkdirs();

            if(!folder.canWrite())
                folder.setWritable(true);

            otpUtil.getQRImage(otpUtil.getGoogleOTPAuthURL(otpMapper.getSecretKey(userPn), loginUser.getEmail(), "bnosoft"),filePath+"/" + userPn +"_QR.png", 900, 900);
            byte[] blob = imageToBlob.convertImageToBlob(filePath+"/" + userPn +"_QR.png");
            otpMapper.addQrBlob(userPn,blob);

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

                    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(inputStream.readAllBytes());

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
                } else
                    // Blob이 없는 경우 처리
                    return null;
            }
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> otpAuth(HttpSession session, String otp){

        User loginUser = (User)session.getAttribute("loginUser");
        int userPn = loginUser.getPn();

        String otpKey = otpUtil.getTOTPCode(otpMapper.getSecretKey(userPn));

        if(otpKey.equals(otp)){
            loginMapper.addLoginHistory(userPn);

            if(otpMapper.otpIsRegistered(userPn) == 0){
                otpMapper.updateOtpIsRegistered(userPn);
                return new ResponseEntity<>("OTP Register Success, Login Success.", HttpStatus.OK);
            }

            return new ResponseEntity<>("OTP Auth Success, Login Success.",HttpStatus.OK);
        } else
            return new ResponseEntity<>("OTP Auth fail.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}