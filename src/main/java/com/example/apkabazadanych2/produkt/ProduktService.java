package com.example.apkabazadanych2.produkt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProduktService {
    @GetMapping
    public List<Produkt> getProdukty(){
        return List.of(new Produkt(1L,"borowik", 50), new Produkt(2L,"pieczarka", 67));
    }
}
