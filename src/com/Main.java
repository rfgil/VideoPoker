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
		
	
		
		FileInputStream cmd = null;
		FileInputStream card = null;
		
		try {
			cmd = new FileInputStream("/home/rafael/cmd");
		} catch (FileNotFoundException e2) {
			System.out.println("cmd input file was not found");
		}
		
		try {
			card = new FileInputStream("/home/rafael/card");
		} catch (FileNotFoundException e2) {
			System.out.println("card input file was not found");
		}
		
		
		VideoPokerPlayer player = new VideoPokerPlayer(500000);
		
		try {
			player.credit(50);
		} catch (NotEnoughBalanceException e) {}
		
		System.out.println(player.getBalance());
		//VideoPoker cena = new InteractiveVideoPoker(player);
		
		VideoPoker cena = new SimulationVideoPoker(player, 1000000, 5);
		
		/*
		VideoPoker cena = null;
		try {
			cena = new DebugVideoPoker(player, cmd, card);
		} catch (InvalidCardStringException e) {
			e.printStackTrace();
		}
		*/
		cena.play();
			
		
		/*
		List<CardPos> hand = new ArrayList<CardPos>();	
		
		hand.add(new CardPos(new Card(CardRank.J, CardSuit.CLUBS), 0));
		hand.add(new CardPos(new Card(CardRank.K, CardSuit.DIAMONDS), 0));
		hand.add(new CardPos(new Card(CardRank.Q, CardSuit.CLUBS), 0));
		hand.add(new CardPos(new Card(CardRank.T, CardSuit.CLUBS), 0));
		hand.add(new CardPos(new Card(CardRank._9, CardSuit.CLUBS), 0));
		
		hand.sort(new CardPosComparator());
		
		HandEvaluator evaluator = new StraightEvaluator();
		
		for(CardPos card_pos : hand){
			evaluator.addCard(card_pos);
		}
		
		System.out.println(evaluator.getAdviceRank());
		System.out.println(evaluator.getAdviceHoldVector());
		System.out.println(evaluator.getHandRank());
		*/
	}

}
