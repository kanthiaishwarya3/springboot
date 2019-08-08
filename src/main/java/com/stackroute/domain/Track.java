package com.stackroute.domain;

import javax.persistence.*;

@Entity   // identify as table in mysql
public class Track
{
    // Declaration of fields in table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String comment;

    public Track() {
    }

// Parameterised Constructor
    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }

    // Getters and Setters
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString() to convert object data into string
    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
