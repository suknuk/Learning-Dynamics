package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.ImitationMethod;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;
import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class ExecutingClass {

	public static void main(String[] args) {
		System.out.println("Hello");

		Game myGame = new Game(50,50, GameType.SNOWDRIFT, Neighborhood.VON_NEUMANN, ImitationMethod.REPLICATOR_RULE);
		CSVWriter writer = new CSVWriter();
		
		for (int i = 0; i <= 50; i++){
			switch(i){
			case 0:
			case 1:
			case 5:
			case 10:
			case 20:
			case 50:
				DrawingClass.createImage(myGame, "Round"+String.format("%02d", myGame.playedRounds)+".png");
				break;
			}
			
			writer.appendEntry(i, myGame.getActionLevel(Strategy.ACTION_ONE));
			
			myGame.playRounds(1);
		}
		
		writer.closeFile();
	}
}
