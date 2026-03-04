package com.example.apkabazadanych2;

import com.example.apkabazadanych2.produkt.Produkt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class BazaDanychAplikacji {

    public static void main(String[] args) {
        SpringApplication.run(BazaDanychAplikacji.class, args);
    }

}
