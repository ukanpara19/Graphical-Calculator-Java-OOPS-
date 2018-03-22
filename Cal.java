package graph.cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import java.awt.Canvas;
import javax.swing.DefaultComboBoxModel;

public class Cal extends JFrame {

	protected static JFrame frame;
	protected static JPanel contentPane;
	protected static JPanel graph_panel;
	protected static int width = 1500;
	protected static int height = 700;;
	protected static JTextField textField_equation;
	protected static JTextField textField_calculator;
	protected static JTextField textField_range_x;
	protected static JTextField textField_range_y;
	protected static Stack<String> stack = new Stack<String>();
	protected static JList<String> history = new JList();
	protected static String[] color = { "Black", "Red", "Green", "Blue" };
	protected static JComboBox comboBox = new JComboBox(color);

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Cal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1223, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		graph_panel = new JPanel();
		graph_panel.setBounds(12, 0, 650, 704);
		contentPane.add(graph_panel);
		graph_panel.setBackground(Color.LIGHT_GRAY);

		JPanel Calc_panel = new JPanel();
		JLabel lbl_equation = new JLabel("Equation :-");

		textField_equation = new JTextField();
		JButton btn_plot = new JButton("Plot");

		JLabel lbl_y = new JLabel("y = f(x) = ");
		JLabel lbl_selectColor = new JLabel("Select Color :-");
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Black", "Red", "Green", "Blue" }));
		JLabel lbl_history = new JLabel("Equation History :");
		Cal_EventListener Cal_actionlistener = new Cal_EventListener();
		Cal_Graph_EventListener graph_actionlistener = new Cal_Graph_EventListener();

		// *************** Action Listeners *****************************************
		btn_plot.addActionListener(Cal_actionlistener); // Button Plot
		btn_plot.addActionListener(graph_actionlistener);// Button Plot
		comboBox.addActionListener(Cal_actionlistener); // ComboBox
		comboBox.addActionListener(graph_actionlistener);// ComboBox

		JButton btn_7 = new JButton("7"); // Button 7
		btn_7.addActionListener(Cal_actionlistener);

		JButton btn_8 = new JButton("8"); // Button 8
		btn_8.addActionListener(Cal_actionlistener);

		JButton btn_9 = new JButton("9"); // Button 9
		btn_9.addActionListener(Cal_actionlistener);

		JButton btn_additon = new JButton("+"); // Button Additon
		btn_additon.addActionListener(Cal_actionlistener);

		JButton btn_raiseto = new JButton("^"); // Button Raise to
		btn_raiseto.addActionListener(Cal_actionlistener);

		JButton btn_Sin = new JButton("sin"); // Button Sin
		btn_Sin.addActionListener(Cal_actionlistener);

		JButton btn_4 = new JButton("4"); // Button 4
		btn_4.addActionListener(Cal_actionlistener);

		JButton btn_5 = new JButton("5"); // Button 5
		btn_5.addActionListener(Cal_actionlistener);

		JButton btn_6 = new JButton("6"); // Button 6
		btn_6.addActionListener(Cal_actionlistener);

		JButton btn_Substract = new JButton("-"); // Button Subtraction
		btn_Substract.addActionListener(Cal_actionlistener);

		JButton btn_sqrt = new JButton("\u221A"); // Button Square Root
		btn_sqrt.addActionListener(Cal_actionlistener);

		JButton btn_Cos = new JButton("cos"); // Button COS
		btn_Cos.addActionListener(Cal_actionlistener);

		JButton btn_1 = new JButton("1"); // Button 1
		btn_1.addActionListener(Cal_actionlistener);

		JButton btn_2 = new JButton("2"); // Button 2
		btn_2.addActionListener(Cal_actionlistener);

		JButton btn_3 = new JButton("3"); // Button 3
		btn_3.addActionListener(Cal_actionlistener);

		JButton btn_Div = new JButton("/"); // Button Divide
		btn_Div.addActionListener(Cal_actionlistener);

		JButton btn_e = new JButton("e"); // Button e
		btn_e.addActionListener(Cal_actionlistener);

		JButton btn_tan = new JButton("tan"); // Button tan
		btn_tan.addActionListener(Cal_actionlistener);

		JButton btn_Dot = new JButton("."); // Button dot
		btn_Dot.addActionListener(Cal_actionlistener);

		JButton btn_0 = new JButton("0"); // Button 0
		btn_0.addActionListener(Cal_actionlistener);

		JButton btn_C = new JButton("C"); // Button Clear

		// **************Action Listener of Button Clear*************
		btn_C.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField_calculator.setText("");
			}
		});

		JButton btn_Mul = new JButton("*"); // Button Multiplication
		btn_Mul.addActionListener(Cal_actionlistener);

		JButton btn_pi = new JButton("\u03C0"); // Button Pie
		btn_pi.addActionListener(Cal_actionlistener);

		JButton btnLn = new JButton("ln"); // Button log
		btnLn.addActionListener(Cal_actionlistener);

		JButton btn_answer = new JButton("Answer"); // Button Answer
		btn_answer.addActionListener(Cal_actionlistener);

		JButton btn_enter = new JButton("Enter"); // Button Enter
		btn_enter.addActionListener(Cal_actionlistener);

		JButton btn_Back = new JButton("\u2190"); // Button Backspace

		// ******************************************Action Listener
		// ******************************************
		btn_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField_calculator.getText().length();
				String remaining;
				if (length > 0) {
					StringBuilder deletechar = new StringBuilder(textField_calculator.getText());
					remaining = deletechar.deleteCharAt(length - 1).toString();
					textField_calculator.setText(remaining);
				} else {
					JOptionPane.showMessageDialog(null, "Nothing to Delete");
				}
			}
		});

		JButton btn_close = new JButton(")"); // Button Close
		btn_close.addActionListener(Cal_actionlistener);

		JButton btn_Del = new JButton("Erase"); // Button Delete
		btn_Del.addActionListener(graph_actionlistener);
		btn_Del.addActionListener(Cal_actionlistener);

		textField_calculator = new JTextField(); // Textfield of Calculator

		JButton btn_open = new JButton("("); // Button Open bracket
		btn_open.addActionListener(Cal_actionlistener);
		Calc_panel.add(btn_open);

		JButton btn_Load = new JButton("Load"); // Button Load

		JLabel lbl_x_axis = new JLabel("X Axis Range"); // label X-axis range

		textField_range_x = new JTextField(); // TextField for range x

		JLabel lbl_Y_axis_range = new JLabel("Y Axis Range"); // Label Y axis range

		textField_range_y = new JTextField(); // Textfield of range Y
		btn_Load.addActionListener(Cal_actionlistener);

		// ***************************Adding components to Panel and setting up
		// appropriate sizes*****************************
		Calc_panel.setBounds(674, 34, 519, 670);
		contentPane.add(Calc_panel);
		Calc_panel.setLayout(new GroupLayout(Calc_panel));

		lbl_equation.setBounds(67, 6, 71, 14);
		Calc_panel.add(lbl_equation);

		textField_equation.setBounds(119, 31, 227, 30);
		Calc_panel.add(btn_plot);

		lbl_y.setBounds(10, 39, 85, 14);
		Calc_panel.add(lbl_y);

		lbl_selectColor.setBounds(67, 166, 129, 14);
		Calc_panel.add(lbl_selectColor);

		comboBox.setBounds(191, 152, 71, 30);
		comboBox.setSize(100, 40);
		Calc_panel.add(comboBox);

		lbl_history.setBounds(15, 220, 180, 14);
		Calc_panel.add(lbl_history);

		history.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.GRAY, null, Color.LIGHT_GRAY, null));
		history.setBounds(160, 210, 200, 73);
		Calc_panel.add(history);

		btn_7.setBounds(67, 378, 60, 35);
		Calc_panel.add(btn_7);

		btn_8.setBounds(129, 378, 60, 35);
		Calc_panel.add(btn_8);

		btn_9.setBounds(191, 378, 60, 35);
		Calc_panel.add(btn_9);

		btn_additon.setBounds(253, 378, 60, 35);
		Calc_panel.add(btn_additon);

		btn_raiseto.setFont(new Font("Lucida", Font.PLAIN, 14));
		btn_raiseto.setBounds(315, 378, 60, 35);
		Calc_panel.add(btn_raiseto);

		btn_Sin.setBounds(377, 378, 80, 35);
		Calc_panel.add(btn_Sin);

		btn_4.setBounds(67, 419, 60, 35);
		Calc_panel.add(btn_4);

		btn_5.setBounds(129, 419, 60, 35);
		Calc_panel.add(btn_5);

		btn_6.setBounds(191, 419, 60, 35);
		Calc_panel.add(btn_6);

		btn_Substract.setFont(new Font("Lucida", Font.PLAIN, 15));
		btn_Substract.setBounds(253, 419, 60, 35);
		Calc_panel.add(btn_Substract);

		btn_sqrt.setBounds(315, 419, 60, 35);
		Calc_panel.add(btn_sqrt);

		btn_Cos.setBounds(377, 419, 80, 35);
		Calc_panel.add(btn_Cos);

		btn_1.setBounds(67, 460, 60, 35);
		Calc_panel.add(btn_1);

		btn_2.setBounds(129, 460, 60, 35);
		Calc_panel.add(btn_2);

		btn_3.setBounds(191, 460, 60, 35);
		Calc_panel.add(btn_3);

		btn_Div.setBounds(253, 460, 60, 35);
		Calc_panel.add(btn_Div);

		btn_e.setFont(new Font("Lucida", Font.PLAIN, 13));
		btn_e.setBounds(315, 460, 60, 35);
		Calc_panel.add(btn_e);

		btn_tan.setBounds(377, 460, 80, 35);
		Calc_panel.add(btn_tan);

		btn_Dot.setFont(new Font("Lucida", Font.PLAIN, 15));
		btn_Dot.setBounds(67, 501, 60, 35);
		Calc_panel.add(btn_Dot);

		btn_0.setBounds(129, 501, 60, 35);
		Calc_panel.add(btn_0);

		btn_C.setBounds(191, 501, 60, 35);
		Calc_panel.add(btn_C);

		btn_Mul.setBounds(253, 501, 60, 35);
		Calc_panel.add(btn_Mul);

		btn_pi.setFont(new Font("Lucida", Font.PLAIN, 14));
		btn_pi.setBounds(315, 501, 60, 35);
		Calc_panel.add(btn_pi);

		btnLn.setBounds(377, 501, 80, 35);
		Calc_panel.add(btnLn);

		btn_answer.setBounds(268, 542, 90, 36);
		Calc_panel.add(btn_answer);

		btn_enter.setBounds(363, 542, 90, 36);
		Calc_panel.add(btn_enter);

		btn_Back.setFont(new Font("Lucida", Font.PLAIN, 15));
		btn_Back.setBounds(187, 542, 71, 36);
		Calc_panel.add(btn_Back);

		btn_close.setFont(new Font("Lucida", Font.PLAIN, 15));
		btn_close.setBounds(129, 542, 52, 36);
		Calc_panel.add(btn_close);

		btn_Del.setBounds(430, 36, 60, 20);
		Calc_panel.add(btn_Del);

		Calc_panel.add(textField_equation);
		textField_equation.setColumns(10);
		btn_plot.setBounds(358, 36, 60, 20);

		textField_calculator.setBounds(50, 326, 400, 30);
		Calc_panel.add(textField_calculator);
		textField_calculator.setColumns(10);

		btn_open.setFont(new Font("Lucida", Font.PLAIN, 15));
		btn_open.setBounds(67, 542, 52, 36);

		btn_Load.setBounds(400, 230, 120, 40);
		Calc_panel.add(btn_Load);

		lbl_x_axis.setBounds(10, 77, 85, 16);
		Calc_panel.add(lbl_x_axis);

		textField_range_x.setBounds(142, 74, 100, 22);
		Calc_panel.add(textField_range_x);
		textField_range_x.setColumns(10);

		lbl_Y_axis_range.setBounds(10, 116, 85, 16);
		Calc_panel.add(lbl_Y_axis_range);

		textField_range_y.setColumns(10);
		textField_range_y.setBounds(142, 113, 100, 22);
		Calc_panel.add(textField_range_y);
	}
}
