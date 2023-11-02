package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProdView {

	private JFrame frame;
	private JButton addButton, removeButton;
	private JTextArea logArea;
	private JProgressBar progressBar;
	private JLabel unitsLabel;
	private JLabel activeProducersLabel;
	private JLabel activeConsumersLabel;
	// private JLabel producerAddedLabel;

	public ProdView() {
		frame = new JFrame("Production Regulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		addButton = new JButton("+");
		removeButton = new JButton("-");
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);

		activeProducersLabel = new JLabel("Active producers: 0");

		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(logArea);
		scrollPanel.setPreferredSize(new Dimension(400,200));
		
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		// activeConsumersLabel = new JLabel("Active Consumers: 0"); // If you want to
		// see active consumers

		unitsLabel = new JLabel("Available Units: 0");

		JPanel progressPanel = new JPanel(new BorderLayout());
		progressPanel.add(unitsLabel, BorderLayout.NORTH);
		progressPanel.add(progressBar, BorderLayout.CENTER);


		
		frame.add(activeProducersLabel, BorderLayout.EAST);
		frame.add(progressPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(scrollPanel, BorderLayout.SOUTH);
		// frame.add(activeConsumersLabel, BorderLayout.SOUTH); // If you want to see
		// how many active consumers
	}

	public void show() {
		frame.setVisible(true);
	}

	public void updateProgressBar(int currentSize, int referenceSize) {
		SwingUtilities.invokeLater(() -> {
			int percent = (int) ((double) currentSize / referenceSize * 100);
			progressBar.setValue(percent);

			if (percent < 50) {
				progressBar.setForeground(Color.RED);
				System.out.println("Updating progressbar...");
			} else {
				progressBar.setForeground(Color.GREEN);
				System.out.println("Updating progressbar...");
			}

			unitsLabel.setText("Available Units: " + currentSize); // Update available units
		});
	}

	public void appendToLog(String message) {
		logArea.append(message + "\n");
		System.out.println("Logged: " + message);
	}

	public void addRemoveButtonListener(ActionListener listener) {
		removeButton.addActionListener(listener);
	}

	public void addAddButtonListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	public void updateActiveProducers(int numActiveProducers) {
		activeProducersLabel.setText("Active Producers: " + numActiveProducers);
	}

	public void updateActiveConsumers(int numActiveConsumers) {
		activeConsumersLabel.setText("Active Consumers: " + numActiveConsumers); ///
	}
	public void updateProducerRemovedStatus(String message) {
	    JOptionPane.showMessageDialog(null, message, "Producer Removed", JOptionPane.INFORMATION_MESSAGE);		
	}
}
