package com.karan.order.api;

import com.karan.order.model.Order;

import java.util.concurrent.CompletableFuture;

public class ExternalApiService {
    public static CompletableFuture<String> verifyOrder(int orderId) {
        return CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            return "Verified order ID: "+ orderId;
        });
    }
}
