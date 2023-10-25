package com.example.securityrole;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    private String nev;
    private String kategorianev;
    private boolean vegetarianus;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getKategorianev() {
        return kategorianev;
    }

    public void setKategorianev(String kategorianev) {
        this.kategorianev = kategorianev;
    }

    public boolean isVegetarianus() {
        return vegetarianus;
    }

    public void setVegetarianus(boolean vegetarianus) {
        this.vegetarianus = vegetarianus;
    }
}
