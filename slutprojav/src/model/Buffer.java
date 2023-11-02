package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

	private Queue<Item> buffer = new LinkedList<Item>();
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	public synchronized void add(Item item) {
		int oldSize = buffer.size();
		buffer.add(item);
		support.firePropertyChange("size", oldSize, buffer.size());
		System.out.println(buffer);
		notify(); // help to awaken any waiting consumer threads when an item is added to the
					// buffer
	}

	public synchronized Item remove() {
		if (buffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int oldSize = buffer.size();
		Item item = buffer.remove();
		support.firePropertyChange("size", oldSize, buffer.size());
		return item;
	}

	public synchronized int getSize() {
		return buffer.size();
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

}