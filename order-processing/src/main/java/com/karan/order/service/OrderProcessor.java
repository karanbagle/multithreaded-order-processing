package com.karan.order.service;
import com.karan.order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


public class OrderProcessor implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final Order order;
    private final CountDownLatch countDownLatch;

    public OrderProcessor(Order order, CountDownLatch countDownLatch) {
        this.order = order;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        logger.info("Processing {}", order);
        try{
            Thread.sleep(1000); //simulate some work
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while processing order {}", order);
        }finally {
            logger.info("Processing completed {}", order);
            countDownLatch.countDown(); //Called when a task is complete

        }
    }
}
