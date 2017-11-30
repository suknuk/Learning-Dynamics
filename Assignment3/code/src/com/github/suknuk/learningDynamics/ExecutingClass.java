package com.github.suknuk.learningDynamics;


public class ExecutingClass {

	public static void main(String[] args) {

		double[] values = {1.3,0.9,  1.1,0.6,  0.5,0.4,  0.3,2};
		
		Game game = new Game(values,Game.Method.SOFTMAX_TIME, 0.1);
		game.playRounds(1000);
		game.getGameInfo();
		

	}

}
