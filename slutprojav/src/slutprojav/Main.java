package slutprojav;

import controller.controller;
import model.Buffer;
import view.ProdView;

public class Main {
	public static void main(String[] args) {

		Buffer buffer = new Buffer();

		ProdView view = new ProdView();

		controller controller = new controller(buffer, view);

		javax.swing.SwingUtilities.invokeLater(() -> {
			view.show();
		});
	}
}