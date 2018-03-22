package graph.cal;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cal window = new Cal();
					Cal_Graph_EventListener utsav = new Cal_Graph_EventListener();
					utsav.setVisible(true);
					utsav.setResizable(false);
					window.setVisible(true);
					window.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
