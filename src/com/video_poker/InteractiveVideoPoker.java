package com.video_poker;

import java.io.InputStreamReader;
import java.util.Scanner;

public class InteractiveVideoPoker extends CommandLineVideoPoker {
	
	public InteractiveVideoPoker(VideoPokerPlayer player) {
		super(player);
	}

	@Override
	public void play() {
		Scanner cmd_reader = new Scanner(new InputStreamReader(System.in));
		boolean isPlaying = true;
		
		while(isPlaying) {
			// Lê linha inteira da linha de comandos
			String str = cmd_reader.nextLine();
			
			Scanner str_scan = new Scanner(str);
			isPlaying = this.commandLinePlay(str_scan.next().charAt(0), str_scan);
			str_scan.close();
		}
		
		cmd_reader.close();
	}
}
