package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.video_poker.*;
import com.video_poker.hand_evaluator.*;
import com.card_game.*;
import com.card_game.exceptions.InvalidCardStringException;
import com.card_game.exceptions.NotEnoughBalanceException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Deck mydeck = new Deck();
		
		VideoPokerPlayer player = new VideoPokerPlayer(Integer.parseInt(args[1]));
		VideoPoker game = null;
		
		switch(args[0]){
			case "-i":
				game = new InteractiveVideoPoker(player);
				
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
				}
				
				try {
					game = new DebugVideoPoker(player, cmd, card);
				} catch (InvalidCardStringException e) {
					System.out.println("provided card file has an error");
				}
				
			case "-s":
				try {
					game = new SimulationVideoPoker(player, Integer.parseInt(args[3]), Integer.parseInt(args[2]));
				} catch(NumberFormatException e){
					System.out.println("imcompatible arguments");
				}
				
				
			case "-g":
				//game = new GuiVideoPoker(player);
				
			default:
				System.out.println("invalid mode");
				System.exit(1);
		}
		
		game.play();
	}

}
