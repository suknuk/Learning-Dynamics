package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.ImitationMethod;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;

public class Test {

	public static void main(String[] args) {
		System.out.println("Hello");

		Game myGame = new Game(50,50, GameType.PRISONERS_DILEMMA, Neighborhood.MOORE, ImitationMethod.HIGHEST_EARNER);
		
		for (int i = 0; i < 50; i++){
			DrawingClass.createImage(myGame, "Round"+String.format("%02d", myGame.playedRounds)+".png");
			myGame.playRounds(1);
		}
		
		/*
		// t=0
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		// t = 1
		myGame.playRounds(1);
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		// t=5
		myGame.playRounds(4);
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		// t=10
		myGame.playRounds(5);
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		// t=20
		myGame.playRounds(10);
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		// t=50
		myGame.playRounds(30);
		DrawingClass.createImage(myGame, "Round"+myGame.playedRounds+".png");
		
		System.out.println(String.format("%02d", 2));
		*/
	}

}
