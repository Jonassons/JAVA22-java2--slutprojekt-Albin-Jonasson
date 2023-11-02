package model;

import java.util.Random;

public class Consumer implements Runnable {
	private final Buffer buffer;
	private volatile boolean isRunning = true;
	private final Random random = new Random();

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				// Determine the consumption interval
				int consumptionInterval = 1000 + random.nextInt(9000); //  between 1 and 10 seconds

				// Log the consumption interval
				System.out.println("Consumer: Consumption interval - " + consumptionInterval + " ms");

				Thread.sleep(consumptionInterval);

				// Consume an item from the buffer
				Item consumedItem = buffer.remove();
				System.out.println("Consumed: " + consumedItem); // item consumed

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRunning() {
		isRunning = false;
	}
}
