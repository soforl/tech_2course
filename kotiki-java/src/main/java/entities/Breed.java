package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "breed")
    private String name;
    @OneToMany(mappedBy = "breedId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Kotik> kotiki;

    public Breed() {
    }

    public Breed(String name) {
        this.name = name;
        kotiki = new ArrayList<>();
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

    public List<Kotik> getKotiki() {
        return kotiki;
    }

    public void setKotiki(List<Kotik> kotiki) {
        this.kotiki = kotiki;
    }
}
