package com.example.bno2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Bno2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bno2Application.class, args);
    }

}

//transaction 
//여러 데이터들중에 하나라도 에러가 뜨면 auto commit이 안되고 rollback을 시켜준다
//controller쪽을 하나의 transaction으로 묶어서: java annotation