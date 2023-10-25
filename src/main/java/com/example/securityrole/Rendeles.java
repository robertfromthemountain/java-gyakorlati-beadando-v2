package com.example.securityrole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "rendeles")
public class Rendeles {
    @Id
    private int az;
    private String pizzanev;
    private int darab;
    private Date felvetel;
    private Date kiszallitas;

    public int getAz() {
        return az;
    }

    public void setAz(int az) {
        this.az = az;
    }

    public String getPizzanev() {
        return pizzanev;
    }

    public void setPizzanev(String pizzanev) {
        this.pizzanev = pizzanev;
    }

    public int getDarab() {
        return darab;
    }

    public void setDarab(int darab) {
        this.darab = darab;
    }

    public Date getFelvetel() {
        return felvetel;
    }

    public void setFelvetel(Date felvetel) {
        this.felvetel = felvetel;
    }

    public Date getKiszallitas() {
        return kiszallitas;
    }

    public void setKiszallitas(Date kiszallitas) {
        this.kiszallitas = kiszallitas;
    }
}
