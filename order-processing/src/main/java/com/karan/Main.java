package com.karan;
import com.karan.order.model.Order;
import com.karan.order.service.OrderProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4); // pool with 4 threads
        final  Logger logger = LoggerFactory.getLogger(Main.class);
        int totalOrders = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalOrders); //A one-time use countdown object used for coordination


        for(int i=1; i<=10; i++) {
            Order order = new Order(i,"Item-"+i);
            executorService.submit(new OrderProcessor(order, countDownLatch));
        }
        try{
            countDownLatch.await(); //wait for all tasks to finish
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("All orders processed!");
        executorService.shutdown();
    }
}