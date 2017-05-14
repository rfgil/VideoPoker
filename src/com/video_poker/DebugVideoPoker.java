package com.video_poker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DebugVideoPoker extends CommandLineVideoPoker{
	
	InputStream cmd_file;
	InputStream card_file;
	
	public DebugVideoPoker(VideoPokerPlayer player, InputStream cmd_file, InputStream card_file) {
		super(player);
		this.cmd_file = cmd_file;
		this.card_file = card_file;
	}

	@Override
	public void play() {
		//Scanner cmd_reader = new Scanner(cmd_file);
		
	}

}
