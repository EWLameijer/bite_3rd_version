package com.ericwubbo.bite.basket;

import com.ericwubbo.bite.basketitem.BasketItem;
import com.ericwubbo.bite.basketitem.BasketItemRepository;
import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    public Basket save(BasketDto basketDto)  {
        var basket = basketRepository.save(new Basket());
        // should likely do a precheck that all ids exist and all counts > 0
        for (BasketItemDto basketItemDto : basketDto.basketItems()) {
            Item item = itemRepository.findById(basketItemDto.itemId()).orElseThrow();
            BasketItem basketItem = new BasketItem(item, basket, basketItemDto.count());
            basketItemRepository.save(basketItem);
            basket.addBasketItem(basketItem);
        }
        return basket;
    }

    public Set<Basket> getLastDaysBaskets() {
        return basketRepository.findByDateTimeIsAfter(LocalDateTime.now().minusDays(1));
    }
}
