package com.karan.order.service;
import com.karan.order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderProcessor implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final Order order;

    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        logger.info("Processing {}", order);
        try{
            Thread.sleep(1000); //simulate some work
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while processing order {}", order);
        }
        logger.info("Completed {}", order);
    }
}
