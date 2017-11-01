package com.github.suknuk.learningDynamics;

import java.util.ArrayList;
import java.util.List;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.ImitationMethod;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;
import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class ExecutingClass {

	public static void main(String[] args) {
		System.out.println("Hello");

		int runs = 100;
		int dim = 8; //dimensions (dim x dim)
		GameType whatGameType = GameType.PRISONERS_DILEMMA;
		Neighborhood whatNeighborhoodType = Neighborhood.MOORE;
		ImitationMethod whatImitationMethod = ImitationMethod.HIGHEST_EARNER;
		
		
		List<Game> myGames = new ArrayList<Game>();
		
		for (int i = 0; i < runs; i++){
			Game newGame = new Game(dim,dim, whatGameType, whatNeighborhoodType, whatImitationMethod);
			myGames.add(newGame);
		}
		
		CSVWriter writer = new CSVWriter(runs);
		
		List<Integer> cooperationLevels;
		
		for (int i = 0; i <= 50; i++){
			switch(i){
			case 0:
			case 1:
			case 5:
			case 10:
			case 20:
			case 50:
				//DrawingClass.createImage(myGames.get(0), ""+whatGameType+"_"+whatNeighborhoodType+"_"+dim+"x"+dim+"_t"+String.format("%02d", myGames.get(0).playedRounds)+".png");
				break;
			}
			
			cooperationLevels = new ArrayList<Integer>();
			for (Game game:myGames){
				cooperationLevels.add(game.getActionLevel(Strategy.ACTION_ONE));
			}
			
			writer.appendEntries(i, cooperationLevels);
			
			for (Game game:myGames){
				game.playRounds(1);
			}
		}
		
		writer.closeFile();
	}
}
