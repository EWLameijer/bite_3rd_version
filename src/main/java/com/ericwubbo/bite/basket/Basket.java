package com.ericwubbo.bite.basket;

import com.ericwubbo.bite.basketitem.BasketItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Basket {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    // without mappedBy, creates a separate join table
    // IF basketItems is present, need @OneToMany
    // new HashSet if I create a Basket myself (based on, for example, a DTO)
    private Set<BasketItem> basketItems = new HashSet<>();

    private LocalDateTime dateTime;

    public Basket() {
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Set<BasketItem> getBasketItems() {
        return basketItems;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void addBasketItem(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    // should at one point also get user.
}
