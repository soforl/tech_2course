package com.example.kotikiservices.entities;

import com.example.kotikiservices.services.OwnerService;
import com.example.kotikiservices.services.OwnerServiceInterface;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kotiki")
public class Kotik {
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
    @Column(name = "colorid")
    private String colorId;
    @Basic
    @Column(name = "breed")
    private String breedId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerid")
    private Owner ownerId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "idkotik1"),
            inverseJoinColumns = @JoinColumn(name = "idkotik2"))
    private List<Kotik> friends;

    @Transient
    private OwnerService ownerService;

    public Kotik() {
    }

    public Kotik(String name, LocalDate birthday, String colorId, String breedId, Integer ownerId) {
        this.name = name;
        this.birthday = birthday;
        this.colorId = colorId;
        this.breedId = breedId;
        this.ownerId = ownerService.findOwner(ownerId);
        friends = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public Owner getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Owner ownerId) {
        this.ownerId = ownerId;
    }
    public List<Kotik> getFriends() {
        return friends;
    }

    public void setFriends(List<Kotik> friends) {
        this.friends = friends;
    }
}
