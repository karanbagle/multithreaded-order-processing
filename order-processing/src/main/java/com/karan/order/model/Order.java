package com.karan.order.model;

public class Order {
    private final int orderId;
    private final String item;

    public Order(int orderId, String item) {
        this.orderId = orderId;
        this.item = item;
    }

    public int getOrderId() {
        return orderId;
    }
    public String getItem() {
        return item;
    }
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", item=" + item + "]";
    }
}
