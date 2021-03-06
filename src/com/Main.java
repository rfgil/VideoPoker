package com;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.video_poker.*;

import com.card_game.exceptions.InvalidCardStringException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Deck mydeck = new Deck();
		
		int initialBalance = 0;
		if (args.length == 0){
			System.out.println("insuficient arguments");
			System.exit(1);
			
		} else if (args.length > 1){
			try {
				initialBalance = Integer.parseInt(args[1]);
			} catch (NumberFormatException e){
				System.out.println("imcompatible arguments");
				System.exit(1);
			}
		}
		
		VideoPokerPlayer player = new VideoPokerPlayer(initialBalance);
		VideoPoker game = null;
		
		switch(args[0]){
			case "-i":
				game = new InteractiveVideoPoker(player);
				break;
				
			case "-d":
				FileInputStream cmd = null;
				FileInputStream card = null;
				
				try {
					cmd = new FileInputStream(args[2]);
					card = new FileInputStream(args[3]);
					
				} catch (FileNotFoundException e) {
					System.out.println("cmd input file was not found");
					System.exit(1);
				} catch(NumberFormatException e){
					System.out.println("imcompatible arguments");
					System.exit(1);
				}
				
				try {
					game = new DebugVideoPoker(player, cmd, card);
				} catch (InvalidCardStringException e) {
					System.out.println("provided card file has an error");
					System.exit(1);
				}
				break;
				
			case "-s":
				try {
					game = new SimulationVideoPoker(player, Integer.parseInt(args[3]), Integer.parseInt(args[2]));
				} catch(NumberFormatException e){
					System.out.println("imcompatible arguments");
					System.exit(1);
				}
				break;
				
				
			case "-g":
				game = new GuiVideoPoker(player);
				
				//game = new GuiVideoPoker(player);
				break;
				
			default:
				System.out.println("invalid mode");
				System.exit(1);
		}
		
		game.play();
	}

}
