package com.video_poker;

//import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;


public class GuiVideoPoker extends VideoPoker {

	private JFrame frame;
	private JTextField txtBalance;
	
	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GuiVideoPoker window = new GuiVideoPoker();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 425, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDeal = new JButton("Deal!");
		btnDeal.setEnabled(false);
		btnDeal.setBounds(235, 318, 151, 25);
		frame.getContentPane().add(btnDeal);
		
		JToggleButton btnHold0 = new JToggleButton("Hold");
		btnHold0.setBounds(12, 211, 70, 25);
		frame.getContentPane().add(btnHold0);
		
		JToggleButton btnHold1 = new JToggleButton("Hold");
		btnHold1.setBounds(94, 211, 70, 25);
		frame.getContentPane().add(btnHold1);
		
		JToggleButton btnHold2 = new JToggleButton("Hold");
		btnHold2.setBounds(176, 211, 70, 25);
		frame.getContentPane().add(btnHold2);
		
		JToggleButton btnHold3 = new JToggleButton("Hold");
		btnHold3.setBounds(258, 211, 70, 25);
		frame.getContentPane().add(btnHold3);
		
		JToggleButton btnHold4 = new JToggleButton("Hold");
		btnHold4.setBounds(340, 211, 70, 25);
		frame.getContentPane().add(btnHold4);
		
		JLabel lblCard0 = new JLabel("Card1");
		lblCard0.setBounds(12, 175, 56, 16);
		frame.getContentPane().add(lblCard0);
		
		JLabel lblCard1 = new JLabel("Card2");
		lblCard1.setBounds(94, 175, 56, 16);
		frame.getContentPane().add(lblCard1);
		
		JLabel lblCard2 = new JLabel("Card3");
		lblCard2.setBounds(176, 175, 56, 16);
		frame.getContentPane().add(lblCard2);
		
		JLabel lblCard3 = new JLabel("Card4");
		lblCard3.setBounds(258, 175, 56, 16);
		frame.getContentPane().add(lblCard3);
		
		JLabel lblCard4 = new JLabel("Card5");
		lblCard4.setBounds(340, 175, 56, 16);
		frame.getContentPane().add(lblCard4);
		
		JButton btnAdvice = new JButton("Advice");
		btnAdvice.setEnabled(false);
		btnAdvice.setBounds(12, 318, 97, 25);
		frame.getContentPane().add(btnAdvice);
		
		JLabel lblAdvice = new JLabel("Advice Label");
		lblAdvice.setBounds(121, 320, 121, 21);
		frame.getContentPane().add(lblAdvice);
		
		JLabel lblBet = new JLabel("Bet Ammount:");
		lblBet.setBounds(12, 280, 97, 25);
		frame.getContentPane().add(lblBet);
		
		JButton btnBet = new JButton("Bet!");
		btnBet.setBounds(235, 280, 151, 25);
		frame.getContentPane().add(btnBet);
		
		JLabel lblCredit = new JLabel("Credit:");
		lblCredit.setBounds(53, 78, 56, 25);
		frame.getContentPane().add(lblCredit);
		
		JButton btnCredit = new JButton("Credit!");
		btnCredit.setBounds(258, 78, 97, 25);
		frame.getContentPane().add(btnCredit);
		
		JLabel label_4 = new JLabel("Players Balance:");
		label_4.setBounds(12, 40, 97, 25);
		frame.getContentPane().add(label_4);
		
		txtBalance = new JTextField();
		txtBalance.setEditable(false);
		txtBalance.setColumns(10);
		txtBalance.setBounds(121, 41, 116, 22);
		frame.getContentPane().add(txtBalance);
		
		JButton btnConfirmHold = new JButton("Comfirm Hold!");
		btnConfirmHold.setEnabled(false);
		btnConfirmHold.setBounds(235, 357, 151, 25);
		frame.getContentPane().add(btnConfirmHold);
		
		JSpinner numCredit = new JSpinner();
		numCredit.setBounds(123, 81, 109, 25);
		frame.getContentPane().add(numCredit);
		
		JSpinner numBetAmmount = new JSpinner();
		numBetAmmount.setBounds(121, 283, 86, 18);
		frame.getContentPane().add(numBetAmmount);
	}
	
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GuiVideoPoker(VideoPokerPlayer player) {
		super(player);
		
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void handAfterDeal(Hand hand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handAfterHold(Hand hand) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void play() {
		initialize();
		this.frame.setVisible(true);
	}
}
