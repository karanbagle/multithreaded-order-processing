package com.karan;
import com.karan.order.model.Order;
import com.karan.order.service.OrderProcessor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4); // pool with 4 threads

        for(int i=1; i<=10; i++) {
            Order order = new Order(i,"Item-"+i);
            executorService.submit(new OrderProcessor(order));
        }
        executorService.shutdown();
    }
}