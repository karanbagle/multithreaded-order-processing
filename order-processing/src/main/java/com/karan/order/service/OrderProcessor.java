package com.karan.order.service;
import com.karan.order.api.ExternalApiService;
import com.karan.order.db.OrderDatabaseService;
import com.karan.order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


public class OrderProcessor implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final Order order;
    private final CountDownLatch countDownLatch;
    private final OrderDatabaseService dbService;

    public OrderProcessor(Order order, CountDownLatch countDownLatch, OrderDatabaseService dbService) {
        this.order = order;
        this.countDownLatch = countDownLatch;
        this.dbService = dbService;
    }


    @Override
    public void run() {
        logger.info("Processing {}", order);
        try{
            Thread.sleep(1000); //simulate some work
            dbService.saveOrder(order);
            logger.info("Saved to DB {}", order);
            ExternalApiService.verifyOrder(order.getOrderId())
                    .thenAccept(result->logger.info("API Result for Order {}: {}",order.getOrderId(),result));
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while processing order {}", order);
        }finally {
            logger.info("Processing completed {}", order);
            countDownLatch.countDown(); //Called when a task is complete

        }
    }
}
