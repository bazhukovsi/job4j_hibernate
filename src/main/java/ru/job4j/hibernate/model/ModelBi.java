package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class ModelBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brands_id")
    private BrandBi brand;

    public static ModelBi of(String name, BrandBi brand) {
        ModelBi model = new ModelBi();
        model.name = name;
        model.brand = brand;
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandBi getBrand() {
        return brand;
    }

    public void setBrand(BrandBi brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelBi modelBi = (ModelBi) o;
        return id == modelBi.id && Objects.equals(name, modelBi.name) && Objects.equals(brand, modelBi.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand);
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
