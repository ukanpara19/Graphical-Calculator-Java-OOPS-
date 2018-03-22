package graph.cal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Cal_Graph_EventListener extends JFrame implements ActionListener {

	int X_center = Cal.graph_panel.getWidth() / 2;
	int Y_center = Cal.graph_panel.getHeight() / 2;
	Color c, d;
	public static int flag = 0;
	private boolean draw = false;
	ArrayList<Integer> X_list = new ArrayList<Integer>();
	ArrayList<Integer> Y_list = new ArrayList<Integer>();
	int i, erase = 0;

	// *************************Action listener for the Plot , Del and combo
	// box***********************
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Plot") {
			draw = true;
			paint(Cal.graph_panel.getGraphics());
		}
		if (e.getSource() == Cal.comboBox) {
			i = Cal.comboBox.getSelectedIndex();
		}
		if (e.getActionCommand() == "Del") {
			draw = true;
			erase = 1;
			paint(Cal.graph_panel.getGraphics());
		}
	}

	// ********************Paint function to draw graphic i.e the Graph
	// ****************************8
	@Override
	public void paint(Graphics g) {
		if (EquationValidation.getInstance().checkEquationValidation(Cal.textField_equation.getText())
				&& (EquationValidation.getInstance().checkValidRange(Cal.textField_range_x.getText()))
				&& (EquationValidation.getInstance().checkValidRange(Cal.textField_range_y.getText()))) {
			if (draw && erase == 0) {
				if (i == 0) {
					g.setColor(Color.black); // Setting Color of the Graph line = Black
				}
				if (i == 1) {
					c = Color.red;
					g.setColor(c); // Setting color of the Graph line = Red
				}
				if (i == 2) {
					c = Color.green;
					g.setColor(c);// Setting color of the graph line = Green
				}
				if (i == 3) {
					c = Color.blue;
					g.setColor(c); // Setting color of the graph line = Blue
				}
				d = Color.BLACK;
				g.setColor(d);
				g.drawLine(X_center, 0, X_center, Cal.graph_panel.getHeight());
				g.drawLine(12, Y_center, Cal.graph_panel.getWidth(), Y_center);
				g.drawString("X-axis " + "", Cal.graph_panel.getWidth() - 40, Y_center); // Giving Y-axis label to the
																							// graph
				g.drawString("Y-axis " + "", X_center, 20); // Giving X-axis Label to the graph
				// g.drawString("-X" + "\u2190" Cal.graph_panel.getWidth() + 40 , yc);
				// g.drawString("-Y" + "\u2193" , X_center, -20);
				fetchCoordinates(Cal.textField_range_x.getText(), Cal.textField_range_y.getText(), c);
			}
		} else if (draw && erase == 1) {
			c = new Color(240, 246, 247);
			fetchCoordinates(Cal.textField_range_x.getText(), Cal.textField_range_y.getText(), c);
		}
	}

	public String reString(String y) {
		char[] ch = { '+', '*', '/', '^', '(', ')', '-' };
		char[] t = { 'p', 'm', 'd', 'r', 'q', 'y', 'x' };
		StringBuilder z = new StringBuilder(y);
		for (int i = 0; i < ch.length; i++) {
			for (int j = 0; j < z.length(); j++) {
				if (z.charAt(j) == ch[i]) {
					z.setCharAt(j, t[i]);
				}
			}
		}
		y = z.toString();
		y = y.replaceAll("p", " + ");
		y = y.replaceAll("m", " * ");
		y = y.replaceAll("d", " / ");
		y = y.replaceAll("r", " ^ ");
		y = y.replaceAll("q", " ( ");
		y = y.replaceAll("y", " ) ");
		y = y.replaceAll("x", " - ");
		return y;

	}
	// *****************Fetching range of Coordinates to plot the
	// Graphs************************

	public void fetchCoordinates(String range_x, String range_y, Color c) {

		String equation_store;
		char[] temp = range_x.toCharArray(); // Character array to convert sand store string into Char
		char[] value_y_coordinates = range_y.toCharArray(); // Character array to convert sand store string into Char
		Cal_EventListener value = new Cal_EventListener();
		// **********Cordinate range for X-axis*******
		try {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == ' ' || temp[i] == ',') {
					continue;
				}
				if (temp[i] == '[' || temp[i] == '(' || temp[i] == '{') {
					continue;
				}
				StringBuffer str = new StringBuffer(); // Creating a String buffer to reserve a room for coordiantes
				while (i < temp.length && ((temp[i] >= '0' && temp[i] <= '9') || temp[i] == '.' || temp[i] == '-')) {
					str.append(temp[i++]); // Appending the String
				}
				X_list.add(Integer.parseInt(str.toString())); // adding the X coordinates to X_list array list

			}
			// **********Cordinate range for Y-axis*******
			for (int i = 0; i < value_y_coordinates.length; i++) {
				if (value_y_coordinates[i] == ' ' || value_y_coordinates[i] == ',') {
					continue;
				}
				if (value_y_coordinates[i] == '[' || value_y_coordinates[i] == '(' || value_y_coordinates[i] == '{')
					continue;
				StringBuffer str = new StringBuffer();
				while (i < value_y_coordinates.length
						&& ((value_y_coordinates[i] >= '0' && value_y_coordinates[i] <= '9')
								|| value_y_coordinates[i] == '.' || value_y_coordinates[i] == '-')) {
					str.append(value_y_coordinates[i++]);// Appending the String
				}
				Y_list.add(Integer.parseInt(str.toString())); // adding the Y coordinates to Y_list array list

			}
			equation_store = Cal.textField_equation.getText();

			// Getting the height and widht of the panel of the Panel
			int low = X_list.get(0);
			int low_y = Y_list.get(0);
			int high_y = Y_list.get(1);
			int high = X_list.get(1);

			int[] X_points = new int[high - low + 2];
			int[] Y_points = new int[high_y - low_y + 2];
			for (int i = low, j = 0, k = 0; i <= high - low + 1 && j <= high - low + 1
					&& k <= high - low + 1; i++, j++, k++) {
				char[] equation = equation_store.toCharArray();
				String result = equation_store.replaceAll("x", "(" + Integer.toString(i) + ")");
				result = reString(result);
				int X_center = Cal.graph_panel.getWidth() / 2 + i * (Cal.graph_panel.getWidth() / (high - low)); // Getting
																													// the
																													// points
																													// of
																													// X
				X_points[j] = X_center;
				Double val_Y = value.calculations(result) * (Cal.graph_panel.getHeight() / 2) / (high_y - low_y); // getting
																													// the
																													// points
																													// of
																													// Y
				if (flag != 0)
					System.exit(0);
				{
					Y_points[k] = Y_center + val_Y.intValue();
					System.out.println("Calculator.panel_graph.getHeight()" + Cal.graph_panel.getHeight());
					System.out.println("calculation " + value.calculations(result));
				}
			}
			System.out.println("Points of X :" + Arrays.toString(X_points)); // Printing the points of x
			System.out.println("Points of Y" + Arrays.toString(Y_points)); // Printing the points of Y
			Cal.graph_panel.getGraphics().drawPolygon(X_points, Y_points, X_points.length); // Drawing the Graph by

			Draw_shape poly = new Draw_shape(X_points, Y_points, c); // Making object of the Draw_Graph class to call it
			poly.drawPolygon(Cal.graph_panel.getGraphics()); // Drawing
		} catch (Exception e) {
			e.printStackTrace();
			Cal.textField_equation.setText(" ");
		}
	}

}
