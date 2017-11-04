package com.github.suknuk.learningDynamics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.ImitationMethod;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;
import com.github.suknuk.learningDynamics.GameInfo.Strategies;
import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class ExecutingClass {

	public static void main(String[] args) {
		System.out.println("Start");

		int simultaneousGames = 50;
		int dim = 50; //dimensions (dim x dim)
		int rounds = 100;
		GameType whatGameType = GameType.ROCK_PAPER_SCISSORS;
		Neighborhood whatNeighborhoodType = Neighborhood.MOORE;
		ImitationMethod whatImitationMethod = ImitationMethod.HIGHEST_EARNER;
		Strategies howManyStrategies = Strategies.THREE;
		List<Integer> strategyDistribution = Arrays.asList(80,60);
		
		
		List<Game> myGames = new ArrayList<Game>();
		
		for (int i = 0; i < simultaneousGames; i++){
			Game newGame = new Game(dim,dim, whatGameType, whatNeighborhoodType, whatImitationMethod, howManyStrategies, strategyDistribution);
			myGames.add(newGame);
		}
		
		CSVWriter writerA = new CSVWriter(simultaneousGames, dim*dim, Strategy.ACTION_ONE, myGames.get(0));
		CSVWriter writerB = new CSVWriter(simultaneousGames, dim*dim, Strategy.ACTION_TWO, myGames.get(0));
		CSVWriter writerC = new CSVWriter(simultaneousGames, dim*dim, Strategy.ACTION_THREE, myGames.get(0));
		
		for (int i = 0; i <= rounds; i++){
			
			
			DrawingClass.createImage(myGames.get(0), ""+whatGameType+"_"+whatNeighborhoodType+"_"+dim+"x"
					+dim+"_t"+String.format("%02d", myGames.get(0).playedRounds)+".png", 
					howManyStrategies);
			
			
			/*
			switch(i){
			case 0:
			case 1:
			case 5:
			case 10:
			case 20:
			case 50:
			case 75:
			case 100:
				DrawingClass.createImage(myGames.get(0), ""+whatGameType+"_"+whatNeighborhoodType+"_"+dim+"x"
						+dim+"_t"+String.format("%02d", myGames.get(0).playedRounds)+".png", 
						howManyStrategies);
				break;
			}
			*/
			
			
			writerA.appendEntries(myGames);
			writerB.appendEntries(myGames);
			writerC.appendEntries(myGames);
			
			for (Game game:myGames){
				game.playRounds(1);
			}
		}
		
		writerA.closeFile();
		writerB.closeFile();
		writerC.closeFile();
		
		System.out.println("Finished");
	}
}
