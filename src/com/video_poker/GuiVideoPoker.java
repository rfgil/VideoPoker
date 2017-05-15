package com.video_poker;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GuiVideoPoker { //extends VideoPoker {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiVideoPoker window = new GuiVideoPoker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiVideoPoker() { //VideoPokerPlayer player) {
		//super(player);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToggleButton btnNewButton = new JToggleButton("Hold");
		btnNewButton.setBounds(12, 275, 72, 25);
		frame.getContentPane().add(btnNewButton);
		
		JToggleButton button = new JToggleButton("Hold");
		button.setBounds(96, 274, 72, 25);
		frame.getContentPane().add(button);
		
		JToggleButton button_1 = new JToggleButton("Hold");
		button_1.setBounds(178, 274, 72, 25);
		frame.getContentPane().add(button_1);
		
		JToggleButton button_2 = new JToggleButton("Hold");
		button_2.setBounds(262, 273, 72, 25);
		frame.getContentPane().add(button_2);
		
		JToggleButton button_3 = new JToggleButton("Hold");
		button_3.setBounds(345, 273, 72, 25);
		frame.getContentPane().add(button_3);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setBounds(12, 311, 72, 25);
		frame.getContentPane().add(btnGo);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(180, 174, 72, 94);
		frame.getContentPane().add(tabbedPane_1);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(262, 174, 72, 94);
		frame.getContentPane().add(tabbedPane_2);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(345, 174, 72, 94);
		frame.getContentPane().add(tabbedPane_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(96, 174, 72, 94);
		frame.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_4.setBounds(12, 174, 71, 94);
		frame.getContentPane().add(tabbedPane_4);
		
		JButton btnSuggestion = new JButton("Suggestion");
		btnSuggestion.setBounds(320, 311, 97, 25);
		frame.getContentPane().add(btnSuggestion);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.setBounds(12, 134, 72, 25);
		frame.getContentPane().add(btnDraw);
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setBounds(12, 13, 87, 18);
		frame.getContentPane().add(lblPlayerName);
		
		JLabel lblBet = new JLabel("bet:");
		lblBet.setBounds(12, 69, 77, 22);
		frame.getContentPane().add(lblBet);
		
		JLabel lblCard = new JLabel("card1");
		lblCard.setBounds(12, 204, 66, 64);
		frame.getContentPane().add(lblCard);
		
		JLabel label = new JLabel("card1");
		label.setBounds(96, 204, 67, 64);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("card1");
		label_1.setBounds(180, 204, 67, 64);
		frame.getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("card1");
		label_3.setBounds(346, 204, 67, 64);
		frame.getContentPane().add(label_3);
		
		JLabel lblCredit = new JLabel("Initial Credit:");
		lblCredit.setBounds(12, 39, 87, 22);
		frame.getContentPane().add(lblCredit);
		
		textField = new JTextField();
		textField.setBounds(116, 10, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 39, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 69, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(214, 382, 106, 25);
		frame.getContentPane().add(textArea);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(164, 382, 38, 25);
		frame.getContentPane().add(lblTotal);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(311, 134, 106, 22);
		frame.getContentPane().add(textArea_1);
	}

	/*
	@Override
	public void handAfterDeal(Hand hand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handAfterHold(Hand hand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	*/
}
