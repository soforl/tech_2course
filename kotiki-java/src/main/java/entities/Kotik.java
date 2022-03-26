package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "kotiki")
public class Kotik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birthday;
    @Enumerated(EnumType.ORDINAL)
    private Color colorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breedid")
    private Breed breedId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerid")
    private Owner ownerId;

    public Kotik() {
    }

    public Kotik(String name, LocalDate birthday, Color colorId, Breed breedId, Owner ownerId) {
        this.name = name;
        this.birthday = birthday;
        this.colorId = colorId;
        this.breedId = breedId;
        this.ownerId = ownerId;
        // friends = new ArrayList<>();
        // id
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

    public Color getColorId() {
        return colorId;
    }

    public void setColorId(Color colorId) {
        this.colorId = colorId;
    }

    public Breed getBreedId() {
        return breedId;
    }

    public void setBreedId(Breed breedId) {
        this.breedId = breedId;
    }

    public Owner getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Owner ownerId) {
        this.ownerId = ownerId;
    }
}
