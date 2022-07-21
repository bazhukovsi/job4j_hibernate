package ru.job4j.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double experience;
    @OneToOne(fetch = FetchType.LAZY)
    private BankVacancies bankVacancies;

    public Candidate() {
    }

    public static Candidate of(String name, double experience, BankVacancies bankVacancies) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.bankVacancies = bankVacancies;
        return candidate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public BankVacancies getBankVacancies() {
        return bankVacancies;
    }

    public void setBankVacancies(BankVacancies bankVacancies) {
        this.bankVacancies = bankVacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
