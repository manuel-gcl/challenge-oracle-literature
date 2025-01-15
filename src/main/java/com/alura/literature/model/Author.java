package com.alura.literature.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="name", nullable = false)
    private String name;
    private int birthYear;
    private int deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(){}

    public Author(String name, int birthYear, int deathYear){
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString(){
        final StringBuilder str = new StringBuilder(50);
        str
                .append("\tAuthor: ").append(name).append("\n")
                .append("\tBirth Year: ").append(birthYear).append("\n")
                .append("\tDeath Year").append(deathYear);
        return str.toString();
    }
}
