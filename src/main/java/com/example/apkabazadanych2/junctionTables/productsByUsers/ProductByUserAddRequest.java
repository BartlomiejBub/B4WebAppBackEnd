package com.example.apkabazadanych2.junctionTables.productsByUsers;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ProductByUserAddRequest {
    public LocalDate date;
    public Long userID;
    public Long productID;
    public Long weight;
}
