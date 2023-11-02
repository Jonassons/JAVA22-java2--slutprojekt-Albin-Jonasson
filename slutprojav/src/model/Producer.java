package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Producer implements Runnable {
    private final Buffer buffer;
    private volatile boolean isRunning = true;
    private final Random random = new Random();
    private static final Logger logger = LogManager.getLogger(Producer.class);
    private int productionInterval; 

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                productionInterval = 1000 + random.nextInt(9000); 

                logger.info("Production interval: " + productionInterval + " ms");

                Thread.sleep(productionInterval);
                buffer.add(new Item(String.valueOf(random.nextInt(100))));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getProductionInterval() {
        return productionInterval; // Method to retrieve the production interval
    }

    public void stopRunning() {
        isRunning = false;
    }
}
