package com.example.kotikiservices.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "birthday")
    private LocalDate birthday;

    @Basic
    @Column(name = "role")
    private String role;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Kotik> kotiki;

    public Owner(){
    }

    public Owner(String name, LocalDate birthday, String role, String username, String password){
        this.name = name;
        this.birthday = birthday;
        this.password=password;
        this.role=role;
        this.username = username;
        kotiki = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Kotik> getKotiki() {
        return kotiki;
    }

    public List<Kotik> AddNewKotik(Kotik kotik){
        kotiki.add(kotik);
        return kotiki;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}