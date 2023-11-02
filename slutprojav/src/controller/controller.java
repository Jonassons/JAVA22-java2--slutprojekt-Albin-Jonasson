package controller;

import model.Buffer;
import model.Producer;
import model.Consumer;
import view.ProdView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class controller implements PropertyChangeListener {
	private static final Logger logger = LogManager.getLogger(controller.class);
	private final Buffer buffer;
	private final List<Producer> producers;
	private final List<Consumer> consumers;
	private final ProdView view;
	private final Random random;
	private final int referenceSize = 100; // Reference size for availability
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int randomCunsumerCount; // !!

	public controller(Buffer buffer, ProdView view) {
		this.buffer = buffer;
		this.producers = new ArrayList<>();
		this.consumers = new ArrayList<>();
		this.view = view;
		this.random = new Random();

		this.buffer.addPropertyChangeListener(this);

		view.addAddButtonListener(new AddButtonListener());
		view.addRemoveButtonListener(new RemoveButtonListener());

		this.randomCunsumerCount = 3 + random.nextInt(13); //
		// view.updateActiveConsumers(this.randomCunsumerCount); // If you want to see
		// how many active consumers

		startRandomConsumers();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("size".equals(evt.getPropertyName())) {
			int currentSize = (int) evt.getNewValue();
			logBufferChange(currentSize);
			view.updateProgressBar(currentSize, referenceSize);
			checkAvailability(currentSize);
		}
	}

	private void logBufferChange(int currentSize) {
		String logEntry = dateFormat.format(new Date()) + " - Current size: " + currentSize;
		logger.info(logEntry);
		writeLogToFile(logEntry + "\n");
	}

	private void writeLogToFile(String logEntry) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
			writer.write(logEntry);
		} catch (IOException e) {
			logger.error("Failed to write log entry to file.", e);
		}
	}

	private void checkAvailability(int currentSize) {
		double percentage = (double) currentSize / referenceSize * 100;

		if (percentage <= 10) {
			logAvailabilityEvent("Low availability");
			logLowAvailability();
			
		} else if (percentage >= 90) {
			logAvailabilityEvent("High availability");
			logHighAvailability();
		}
	}

	private void logAvailabilityEvent(String event) {
		String logEntry = dateFormat.format(new Date()) + " - " + event;
		logger.info(logEntry);
		writeLogToFile(logEntry + "\n");
	}

	private class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addProducer();
		}
	}

	private class RemoveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			removeProducer();
		}
	}

	public void addProducer() {
	    Producer producer = new Producer(buffer);
	    producers.add(producer);
	    new Thread(producer).start();
	    try {
	        Thread.sleep(100); // A brief pause to allow the thread to start and update the production interval
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    int interval = producer.getProductionInterval(); // Access the production interval
	    logWorkerChange("Producer added with interval: " + interval + " ms");
	    view.updateActiveProducers(producers.size());
	}

	public void removeProducer() {
		if (!producers.isEmpty()) {
			Producer p = producers.remove(producers.size() - 1);
			p.stopRunning();
			logWorkerChange("Producer removed");
			 view.updateActiveProducers(producers.size()); 
			 
		}
	}

	private void addConsumer() {
		if (consumers.size() < 15) {
			Consumer consumer = new Consumer(buffer);
			consumers.add(consumer);
			new Thread(consumer).start();
			// view.appendToLog("A consumer has been added.");

		//	logWorkerChange("Consumer added");
			// view.updateActiveConsumers(consumers.size()); // If you want to see how many
			// active consumers
		}
	}

	private void startRandomConsumers() {
		randomCunsumerCount = 3 + random.nextInt(13);
		for (int i = 0; i < randomCunsumerCount; i++) {
			addConsumer();
		}
	}

	private void logWorkerChange(String event) {
		String logEntry = dateFormat.format(new Date()) + " - " + event;
		logger.info(logEntry);
		writeLogToFile(logEntry + "\n");
		view.appendToLog(logEntry);
	}

	public int getBufferSize() {
		return buffer.getSize();
	}
	private void logLowAvailability() {
	    String event = "Warning lower than 10% availability";
	    logAndDisplay(event, " 10%");
	}

	private void logHighAvailability() {
	    String event = "Warning availability over 90%!";
	    logAndDisplay(event, "90%!");
	}

	private void logAndDisplay(String event, String level) {
	    String logEntry = dateFormat.format(new Date()) + " - " + event;

	    if (level.equals("WARNING")) {
	        logger.warn(logEntry);
	    } else if (level.equals("MESSAGE")) {
	        logger.info(logEntry);
	    }

	    writeLogToFile(logEntry + "\n");
	    view.appendToLog(logEntry);
	}
}
