package com.ericwubbo.bite.tag;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
