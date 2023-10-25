package com.example.securityrole;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "uzenetek")
public class Kapcsolat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String uzenet;
    private String felado;
    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    public String getFelado() {
        return felado;
    }

    public void setFelado(String felado) {
        this.felado = felado;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
