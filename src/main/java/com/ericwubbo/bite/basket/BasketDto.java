package com.ericwubbo.bite.basket;

record BasketItemDto(long itemId, int count) {
}

public record BasketDto(BasketItemDto[] basketItems) {
}
