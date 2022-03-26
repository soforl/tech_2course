package entities;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idKotik1;
    private Integer idKotik2;

    public Friends(Integer idKotik1, Integer idKotik2) {
        this.idKotik1 = idKotik1;
        this.idKotik2 = idKotik2;
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
        return idKotik1;
    }

    public void setIdKotik1(Integer idKotik1) {
        this.idKotik1 = idKotik1;
    }

    public Integer getIdKotik2() {
        return idKotik2;
    }

    public void setIdKotik2(Integer idKotik2) {
        this.idKotik2 = idKotik2;
    }
}
