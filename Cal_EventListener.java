package graph.cal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Cal_EventListener implements ActionListener {
	private static Double submit;
	private static Double answer;
	DefaultListModel dm = new DefaultListModel();

	// **************************8Action Perform for all the buttons if the
	// calculator**********************
	@Override
	public void actionPerformed(ActionEvent e) {

		String character = " " + e.getActionCommand() + " ";
		String end_bracket = e.getActionCommand() + " ( ";

		if (e.getActionCommand() == "7") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "8") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "9") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "4") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "5") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "6") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "1") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "2") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "3") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "0") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == ".") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "+") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "-") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "*") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "/") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "(") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == ")") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "^") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + character);
		}
		if (e.getActionCommand() == "\u221A") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + end_bracket);
		}
		if (e.getActionCommand() == "sin") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + end_bracket);
		}
		if (e.getActionCommand() == "cos") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + end_bracket);
		}
		if (e.getActionCommand() == "tan") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + end_bracket);
		}
		if (e.getActionCommand() == "ln") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "e") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "\u03C0") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + e.getActionCommand());
		}
		if (e.getActionCommand() == "Enter") {
			submit = calculations(Cal.textField_calculator.getText().toString());
			answer = submit;
			Cal.textField_calculator.setText(submit.toString());
		}
		if (e.getActionCommand() == "Answer") {
			Cal.textField_calculator.setText(Cal.textField_calculator.getText() + " " + answer.toString());
		}
		String select = e.getActionCommand();
		if (select == "Plot") {
			if (EquationValidation.getInstance().checkEquationValidation(Cal.textField_equation.getText())) {
				dm.addElement(Cal.textField_equation.getText());
				Cal.history.setModel(dm);
				Cal.textField_equation.setText("");
			}
		}
		if (select == "Erase") {
			dm.removeElementAt(Cal.history.getSelectedIndex());
			Cal.textField_equation.setText("");
			// Cal.graph_panel.removeAll();
		}
		if (select == "Load") {
			Cal.textField_equation.setText(Cal.history.getSelectedValue());
		}
		if (e.getSource() == Cal.comboBox) {
			int i = Cal.comboBox.getSelectedIndex();
			if (i == 0) {
				Cal.textField_equation.setForeground(Color.black);
			}
			if (i == 1) {
				Cal.textField_equation.setForeground(Color.red);
			}

			if (i == 2) {
				Cal.textField_equation.setForeground(Color.green);
			}

			if (i == 3) {
				Cal.textField_equation.setForeground(Color.blue);
			}
		}
	}

	// ****************The function does the calcation usinf BODMAS
	// implmentation*******************
	public double calculations(String equation) {

		Character current_char;
		Character next_char;
		int negative_temp = 0;
		Character operator;
		char[] c = equation.toCharArray();

		Stack<Double> num = new Stack<Double>(); // Creating a stack to store all the
		Stack<Character> char_op = new Stack<Character>(); // Creating a stack to store character operands
		try {
			for (int i = 0; i < c.length; i++) {
				current_char = c[i];
				if (current_char == '(' && i < c.length - 6) {
					next_char = c[i + 3];
					int j = i + 5;

					if (current_char == '(' && next_char == '-') {
						StringBuffer negative = new StringBuffer();
						while (j < c.length && c[j] >= '0' && c[j] <= '9') {
							negative.append(c[j++]);
						}
						String negativeValue = "-" + negative.toString();
						negative_temp = 1;
						num.push(Double.parseDouble(negativeValue));
						i = j;
					}
				} else if (c[i] == ' ')
					continue;
				else if (c[i] == '\u221A')
					char_op.push(c[i]);
				else if (c[i] == 's' || c[i] == 'c' || c[i] == 't' || c[i] == 'l') {
					StringBuffer sb = new StringBuffer();
					while (i < c.length && c[i] != ' ') {
						sb.append(c[i++]);
					}
					char_op.push(sb.charAt(0)); // Pushing to the character operand Stack
				}

				else if ((c[i] >= '0' && c[i] <= '9') || c[i] == '.') {
					StringBuffer buffer = new StringBuffer(); // If there is the more than one number , StringBuffer
																// handles it
					while (i < c.length && ((c[i] >= '0' && c[i] <= '9') || c[i] == '.')) {
						buffer.append(c[i++]);
					}
					num.push(Double.parseDouble(buffer.toString())); // pushing to the Stack
				} else if (c[i] == 'e') {
					num.push(Math.E); // Pushing to the number Stack

				} else if (c[i] == '\u03C0') {
					num.push(Math.PI); // Pushing to the nunber stack
				}

				else if (c[i] == '(')
					char_op.push(c[i]); // Pushing to the character operand Stack

				else if (c[i] == ')') {
					if (char_op.peek() == '(') // Looking of "( " at the top of the stack
						char_op.pop();// Pushing to the character operand Stack
					else {
						while (char_op.peek() != '(') {
							operator = char_op.peek();
							if (operator == '\u221a') {
								if (!num.isEmpty()) {
									num.push(Math.sqrt(num.pop()));// Pushing to the nunber stack
								}
								char_op.pop();
							} else if (operator == 's') {
								if (!num.isEmpty()) {
									num.push(Math.sin(num.pop()));// Pushing to the nunber stack
								}
								char_op.pop();
							} else if (operator == 'c') {
								if (!num.isEmpty()) {
									num.push(Math.cos(num.pop()));// Pushing to the nunber stack
								}
								char_op.pop();
							} else if (operator == 't') {
								if (!num.isEmpty()) {
									num.push(Math.tan(num.pop()));// Pushing to the nunber stack
								}
								char_op.pop();
							} else if (operator == 'l') {
								if (!num.isEmpty()) {
									num.push(Math.log(num.pop()));// Pushing to the nunber stack
								}
								char_op.pop();
							} else {
								num.push(operation(operator, num.pop(), num.pop()));
								char_op.pop();
							}
						}
					}

				} else if (c[i] == '+' || c[i] == '-' || c[i] == '*' || c[i] == '/' || c[i] == '^') {
					while (!char_op.empty() && precedence(c[i], char_op.peek())) {

						operator = char_op.pop();

						if (operator == '\u221a') {
							if (!num.isEmpty()) {
								num.push(Math.sqrt(num.pop()));// Pushing to the nunber stack
							}
						} else if (operator == 's') {
							if (!num.isEmpty()) {
								num.push(Math.sin(num.pop()));// Pushing to the nunber stack
							}
						} else if (operator == 'c') {
							if (!num.isEmpty()) {
								num.push(Math.cos(num.pop()));// Pushing to the nunber stack
							}
						} else if (operator == 't') {
							if (!num.isEmpty()) {
								num.push(Math.tan(num.pop()));// Pushing to the nunber stack
							}
						} else if (operator == 'l') {
							if (!num.isEmpty()) {
								num.push(Math.log(num.pop()));// Pushing to the nunber stack
							}
						} else
							num.push(operation(operator, num.pop(), num.pop()));// Pushing to the nunber stack
					}
					char_op.push(c[i]);
				}

				else {
					operator = char_op.pop();
					if (operator == '\u221a') {
						if (!num.isEmpty()) {
							num.push(Math.sqrt(num.pop()));// Pushing to the nunber stack
						}
					} else if (operator == 's') {
						if (!num.isEmpty()) {
							num.push(Math.sin(num.pop()));// Pushing to the nunber stack
						}
					} else if (operator == 'c') {
						if (!num.isEmpty()) {
							num.push(Math.cos(num.pop()));// Pushing to the nunber stack
						}
					} else if (operator == 't') {
						if (!num.isEmpty()) {
							num.push(Math.tan(num.pop()));// Pushing to the nunber stack
						}
					} else if (operator == 'l') {
						if (!num.isEmpty()) {
							num.push(Math.log(num.pop()));// Pushing to the nunber stack
						}
					}

				}

			}

			while (!char_op.empty()) {
				if (char_op.peek() == '(')
					char_op.pop();
				operator = char_op.pop();
				if (operator == '\u221a') {
					if (!num.isEmpty()) {
						num.push(Math.sqrt(num.pop()));// Poping from the number stack
					}
				} else if (operator == 's') {
					if (!num.isEmpty()) {
						num.push(Math.sin(num.pop()));// Poping from the number stack
					}
				}

				else if (operator == 'c') {
					if (!num.isEmpty()) {
						num.push(Math.cos(num.pop()));// Poping from the number stack
					}

				} else if (operator == 't') {
					if (!num.isEmpty()) {
						num.push(Math.tan(num.pop()));// Poping from the number stack
					}

				} else if (operator == 'l') {
					if (!num.isEmpty()) {
						num.push(Math.log(num.pop()));// Poping from the number stack
					}

				} else if (operator == 'e') {
					if (!num.isEmpty()) {
						num.push(Math.exp(num.pop()));// Poping from the number stack
					}
				} else {
					num.push(operation(operator, num.pop(), num.pop()));
				}
			}
		} catch (Exception e) {
		}
		if (!num.isEmpty())
			return num.pop();
		else {
			JOptionPane.showMessageDialog(null, "Error!! ");
			Cal_Graph_EventListener.flag = 1;
			return 0;
		}
	}

	// **************Applying BODMAS**********************************
	private boolean precedence(char c, Character g) {
		if (g == '(' || g == ')')
			return false;
		if ((c == '*' || c == '/') && (g == '+' || g == '-'))
			return false;
		if ((c == '^' || c == '\u221A') && (g == '+' || g == '-' || g == '*' || g == '/'))
			return false;
		if ((c == 'l' || c == 's' || c == 'c' || c == 't')
				&& (g == '+' || g == '-' || g == '*' || g == '/' || c == '^' || c == '\u221A'))
			return false;
		else
			return true;
	}

	// **********************funtion to perform Arithmatic operations on
	// numbers**********************
	public Double operation(Character operation, Double x, Double y) {
		switch (operation) {
		case '+':
			return x + y;
		case '-':
			return x - y;
		case '*':
			return x * y;
		case '/':
			if (x == 0.0) {
				JOptionPane.showMessageDialog(null, "Error!! Incorrect input, connot be divided by 0");
				break;
			} else
				return y / x;
		case '^':
			return Math.pow(x, y);
		case '\u221A':
			if (x < 0) {
				JOptionPane.showMessageDialog(null, "Error!! Negative squareroot is not valid, Enter valid innput");
				Cal_Graph_EventListener.flag = 1;
				throw new UnsupportedOperationException("Error!! Negative squareroot is not valid, Enter valid innput");
			}
			return Math.pow(x, y);
		}
		return 0.0;
	}
}
