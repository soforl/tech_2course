package com.example.kotiki4.entities;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "idkotik1")
    private Integer idkotik1;
    @Basic
    @Column(name = "idkotik2")
    private Integer idkotik2;

    public Friends(Integer idKotik1, Integer idKotik2) {
        this.idkotik1 = idKotik1;
        this.idkotik2 = idKotik2;
    }

    public Friends() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKotik1() {
        return idkotik1;
    }

    public void setIdKotik1(Integer idKotik1) {
        this.idkotik1 = idKotik1;
    }

    public Integer getIdKotik2() {
        return idkotik2;
    }

    public void setIdKotik2(Integer idKotik2) {
        this.idkotik2 = idKotik2;
    }
}