package com.example.apkabazadanych2.produkt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/produkt")
public class ProduktController {

    private final ProduktService produktService;

    @Autowired
    public ProduktController(ProduktService produktService){

        this.produktService = produktService;

    }

    @GetMapping
    public List<Produkt> getProdukty(){
        return produktService.getProdukty();
    }
}
