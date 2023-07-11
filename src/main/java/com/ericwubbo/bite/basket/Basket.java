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
    // IF basketItems is present, need @OneToMany
    // new HashSet<> necessary if I create a basket
    private Set<BasketItem> basketItems = new HashSet<>();

    private LocalDateTime dateTime;

    public Basket() {
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Set<BasketItem> getBasketItems() {
        return new HashSet(basketItems);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // for persistence
    public void removeBasketItems() {
        basketItems.clear();
    }

    public void addBasketItem(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    // should at one point also get user.
}
