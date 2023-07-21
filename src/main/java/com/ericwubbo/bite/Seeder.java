package com.ericwubbo.bite;

import com.ericwubbo.bite.basket.Basket;
import com.ericwubbo.bite.basket.BasketRepository;
import com.ericwubbo.bite.basketitem.BasketItem;
import com.ericwubbo.bite.basketitem.BasketItemRepository;
import com.ericwubbo.bite.item.Item;
import com.ericwubbo.bite.item.ItemRepository;
import com.ericwubbo.bite.tag.Tag;
import com.ericwubbo.bite.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... args) {
        seedTags();
        seedItems();
        seedBasket();
    }

    private void seedTags() {
        long count = tagRepository.count();
        if (count == 0) {
            System.out.println("Seeding tags");
            tagRepository.save(new Tag("fruit"));
            tagRepository.save(new Tag("biological"));
            count = tagRepository.count();
        }
        System.out.println(count + " tags in the database.");
    }

    private void seedBasket() {
        long count = basketRepository.count();
        if (count == 0) {
            System.out.println("Seeding basket");
            Item apples = itemRepository.findByName("apples").stream().findFirst().get();
            Item pears = itemRepository.findByName("prunes").stream().findFirst().get();
            var basket = new Basket();
            BasketItem applesOrder = new BasketItem(apples, basket, 1);
            BasketItem pearsOrder = new BasketItem(pears, basket, 2);
            basketRepository.save(basket); // does not save the basketitems without the @OneToMany
            basketItemRepository.save(applesOrder);
            basketItemRepository.save(pearsOrder);
            count = basketRepository.count();
            // even does not save the basketitems WITH the @OneToMamy
        }
        System.out.println(count + " baskets in the database.");
    }

    private void seedItems() {
        long count = itemRepository.count();
        if (count == 0) {
            System.out.println("Seeding items");
            var fruitTag = tagRepository.findByName("fruit").get();
            var biologicalTag = tagRepository.findByName("biological").get();
            List<Item> items = List.of(
                    new Item("apples", "2.25", fruitTag, biologicalTag),
                    new Item("bananas", "3.79", fruitTag),
                    new Item("pears", "4.50", fruitTag),
                    new Item("prunes", "1.20", fruitTag, biologicalTag));
            for (Item item : items) {
                itemRepository.save(item);
            }
            count = itemRepository.count();
        }
        System.out.println(count + " items in the database.");
    }
}