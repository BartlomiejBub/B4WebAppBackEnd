package com.example.apkabazadanych2.produkt;

public class Produkt {
    public Long id;
    public String nazwa;
    public Integer kalorycznosc;

    public Produkt(Long id, String nazwa, Integer kalorycznosc) {
        this.id = id;
        this.nazwa = nazwa;
        this.kalorycznosc = kalorycznosc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKalorycznosc() {
        return kalorycznosc;
    }

    public void setKalorycznosc(Integer kalorycznosc) {
        this.kalorycznosc = kalorycznosc;
    }

    public Produkt(Integer kalorycznosc, String nazwa) {
        this.kalorycznosc = kalorycznosc;
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", kalorycznosc=" + kalorycznosc +
                '}';
    }
}
