package com.ericwubbo.bite.item;

import com.ericwubbo.bite.tag.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToMany
    private Set<Tag> tags;

    private Item() {}

    public Item(String name, String price, Tag... tags) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.tags = Set.of(tags);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
