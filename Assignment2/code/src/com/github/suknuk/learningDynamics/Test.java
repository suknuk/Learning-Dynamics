package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;

public class Test {

	public static void main(String[] args) {
		System.out.println("Hello");

		Game myGame = new Game(50,50, GameType.PRISONERS_DILEMMA, Neighborhood.MOORE);
		myGame.playRounds(1);
	
		DrawingClass.createImage(myGame);
		System.out.println("Printed image");
		
	}

}
