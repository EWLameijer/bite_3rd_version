package com.ericwubbo.bite.basket;

import com.ericwubbo.bite.basketitem.BasketItem;
import com.ericwubbo.bite.basketitem.BasketItemRepository;
import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/baskets")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @GetMapping
    public Set<Basket> test() {
        return basketService.getLastDaysBaskets();
    }

    @PostMapping
    public Basket post(@RequestBody BasketDto basketDto) {
        // note: should likely also make a basketDto.toBasket() method, but that would distract from the Service-discussion
        return basketService.save(basketDto);
    }
}
