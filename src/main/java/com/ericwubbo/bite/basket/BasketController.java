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

    @PostMapping
    public Basket post(@RequestBody Basket basket) {
        // detached entity passed to persist: com.ericwubbo.bite.basketitem.BasketItem:
        // so need to first get basketItemdata from basket, replacing by nulls, save the remaining basket,
        // then create the items with the basket link,
        // approach 1: works! (though a bit finicky)
        var basketItems = basket.getBasketItems();
        basket.removeBasketItems();
        var savedBasket = basketRepository.save(basket);
        for( BasketItem basketItem: basketItems) {
            basketItem.setBasket(savedBasket);
            Item item = itemRepository.findById(basketItem.getId()).orElseThrow();
            basketItem.setItem(item);
            // need to remove ID? No, I don't... Yes, I do!, else in next save it erroneously overwrites the old element with the given ID...
            basketItem.setId(null);
            basketItemRepository.save(basketItem);
        }
        // Can I achieve the same through linking the basketItems directly?
        // no: detached entity passed to persist: com.ericwubbo.bite.basketitem.BasketItem
//        var basketItems = basket.getBasketItems();
//        for (BasketItem basketItem : basketItems) {
//            basketItem.setBasket(basket);
//            Item item = itemRepository.findById(basketItem.getId()).orElseThrow();
//            basketItem.setItem(item);
//            // need to remove ID? No, I don't...
//        }

        return savedBasket;
    }

}
