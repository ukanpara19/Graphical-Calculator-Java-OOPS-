package graph.cal;

import javax.swing.JOptionPane;

public class EquationValidation {
	private static EquationValidation verification;

	public static EquationValidation getInstance() {
		if (verification == null) {
			verification = new EquationValidation();
		}
		return verification;
	}

	// *************Function to check the valdation of the equation that is to be
	// plotted**************
	public boolean checkEquationValidation(String equation) {
		Character current_char, next_char;
		int count = 0, temp = 0;
		char[] c = equation.toCharArray();

		int i;

		try {
			for (i = 0; i < c.length - 1; i++) {
				current_char = c[i];
				next_char = c[i + 1];

				if (i == c.length - 2 && c[c.length - 2] != ')' && (next_char == ')')) {
					count--;
					continue;
				} else if (i == c.length - 2 && (c[c.length - 2] == ')') && (c[c.length - 1] == ')')) {
					count = count - 2;
					continue;
				}
				if (i == c.length - 1 && (next_char == '+' || next_char == '-' || next_char == '*' || next_char == '/'
						|| next_char == '^')) {
					temp = 1;
					continue;
				}
				if ((current_char >= '0' && current_char <= '9') && (next_char == 'x' || next_char == 'X')) {
					temp = 1;
					continue;
				}
				if (c[i] == ' ')
					continue;
				else if ((current_char >= '0' && current_char <= '9')
						&& ((next_char >= '0' && next_char <= '9') || (next_char == '+' || next_char == '-'
								|| next_char == '*' || next_char == '/' || next_char == '^')))
					continue;

				else if (current_char == 'x' || current_char == 'X')
					continue;

				else if ((current_char == '.') && (next_char >= '0' && next_char <= '9'))
					continue;

				else if ((c[i] >= '0' && c[i] <= '9')
						|| (c[i] == '+' || c[i] == '-' || c[i] == '*' || c[i] == '/' || c[i] == '^')) {
					continue;
				} else if ((current_char == 's' && next_char == 'i' && c[i + 2] == 'n')
						|| (current_char == 'c' && next_char == 'o' && c[i + 2] == 's')
						|| (current_char == 't' && next_char == 'a' && c[i + 2] == 'n')) {
					i = i + 2;
					continue;
				} else if (current_char == '(') {
					count++;
					continue;
				} else if (current_char == ')') {
					count--;
					continue;
				} else if ((current_char == '+' || current_char == '-' || current_char == '*' || current_char == '/'
						|| current_char == '^')
						&& (next_char == '+' || next_char == '-' || next_char == '*' || next_char == '/'
								|| next_char == '^')) {
					temp = 1;
					continue;
				} else if ((i == c.length - 1)
						&& (c[i] == '+' || c[i] == '-' || c[i] == '*' || c[i] == '/' || c[i] == '^')) {
					temp = 1;
					continue;
				} else
					temp = 1;
			}
			if (c.length == 1 && c[0] != 'x') {
				temp = 1;
			}
			if (count > 0) { // if Count > 0 , then a dialog box pops out with Error message
				JOptionPane.showMessageDialog(null, "ERROR!! ,Please enter equation in a valid form");
				Cal.textField_equation.setText("");
				Cal_Graph_EventListener.flag = 1;
				return false;
			}
			if (temp != 0) { // if Count is not equal to 0, then a dialog box pops out with Error message
				JOptionPane.showMessageDialog(null, "ERROR!! ,Please enter equation in a valid form");
				Cal.textField_equation.setText("");
				Cal_Graph_EventListener.flag = 1;
				return false;
			} else
				return true;
		} catch (Exception e) { // Same as above
			temp = 1;
			JOptionPane.showMessageDialog(null, "ERROR!! ,Please enter equation in a valid form");
			Cal.textField_equation.setText(" ");
			Cal_Graph_EventListener.flag = 1;
			return false;
		}

	}

	// ************************Function to check if the range entered is valid or
	// not ***************************
	public boolean checkValidRange(String equation) {
		char[] c = equation.toCharArray();
		int temp = 0, count = 0;
		try {
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ' ')
					continue;
				else if ((i < c.length && (c[i] >= '0' && c[i] <= '9')) || c[i] == '.' || c[i] == '-' || c[i] == ',') {
					temp = 0;
					continue;
				} else if (c[i] == '[' || c[i] == '(' || c[i] == '{') {
					count++;
					continue;
				} else if (c[i] == ']' || c[i] == ')' || c[i] == '}') {
					count--;
					continue;
				} else
					temp = 1;
			}
			// ************************Condition to check if the range entered is valid or
			// not****************************
			if (count > 0 || temp != 0) {
				JOptionPane.showMessageDialog(null, "Enter a valid range"); // Pops up a Diload box saying range is not
																			// valid
				Cal.textField_range_x.setText("");
				Cal.textField_range_y.setText("");
				Cal_Graph_EventListener.flag = 1;
				return false;
			} else
				return true;
		} catch (Exception e) {
			temp = 1;
			JOptionPane.showMessageDialog(null, "Enter a valid range"); // Pops up a Diload box saying range is not
																		// valid
			Cal_Graph_EventListener.flag = 1;
			Cal.textField_range_x.setText("");
			Cal.textField_range_y.setText("");
			return false;
		}

	}
}
