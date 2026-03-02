package com.example.apkabazadanych2;

import com.example.apkabazadanych2.produkt.Produkt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class ApkaBazaDanych2Application {

    public static void main(String[] args) {
        SpringApplication.run(ApkaBazaDanych2Application.class, args);
    }

}
