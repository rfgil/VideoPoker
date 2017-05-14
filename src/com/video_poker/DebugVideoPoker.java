package com.video_poker;

import java.io.InputStream;
import java.util.Scanner;

import com.card_game.exceptions.InvalidCardStringException;

public class DebugVideoPoker extends CommandLineVideoPoker{
	
	InputStream cmd_file;
	
	public DebugVideoPoker(VideoPokerPlayer player, InputStream cmd_file, InputStream card_file) throws InvalidCardStringException {
		super(player, card_file);
		this.cmd_file = cmd_file;
	}

	@Override
	public void play() {
		Scanner cmd_reader = new Scanner(cmd_file);
		
		while(cmd_reader.hasNext()) {
			char command = cmd_reader.next().charAt(0);
			
			System.out.print("action: " + command);
			this.commandLinePlay(command, cmd_reader, true);
		}
		
		cmd_reader.close();
	}

}
