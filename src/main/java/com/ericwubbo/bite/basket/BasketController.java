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
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public Set<Basket> test() {
        return basketRepository.findByDateTimeIsAfter(LocalDateTime.now().minusDays(1));
    }

    record BasketItemDto(long itemId, int count) {
    }

    record BasketDto(BasketItemDto[] basketItems) {
    }

    @PostMapping
    public Basket post(@RequestBody BasketDto basketDto) {
        var basket = basketRepository.save(new Basket());
        // should likely do a precheck that all ids exist and all counts > 0
        for (BasketItemDto basketItemDto : basketDto.basketItems()) {
            Item item = itemRepository.findById(basketItemDto.itemId).orElseThrow();
            BasketItem basketItem = new BasketItem(item, basket, basketItemDto.count);
            basketItemRepository.save(basketItem);
            basket.addBasketItem(basketItem);
        }
        return basket;
    }

}
