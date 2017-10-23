package com.github.suknuk.learningDynamics;

public class GameInfo {
	public static enum Strategy {
		ACTION_ONE, ACTION_TWO;
	}
	
	public static enum GameType {
		PRISONERS_DILEMMA, SNOWDRIFT;
	}
	
	public static enum Neighborhood {
		MOORE, VON_NEUMANN;
	}
	
	int R=0,S=0,T=0,P=0;
	
	public GameInfo(GameType gameType){
		if (gameType == GameType.PRISONERS_DILEMMA) {
			T = 10;
			R = 7;
			P = 0;
			S = 0;
		} else if(gameType == GameType.SNOWDRIFT) {
			T = 10;
			R = 7;
			P = 0;
			S = 3;
		}
	}
	
	public int calculatePayoff(Player p1, Player p2){
		if (p1.getStrategy() == Strategy.ACTION_ONE) {
			if (p2.getStrategy() == Strategy.ACTION_ONE) {
				return R;
			} else if (p2.getStrategy() == Strategy.ACTION_TWO) {
				return S;
			}
		} else if (p1.getStrategy() == Strategy.ACTION_TWO) {
			if (p2.getStrategy() == Strategy.ACTION_ONE) {
				return T;
			} else if (p2.getStrategy() == Strategy.ACTION_TWO) {
				return P;
			}
		}
		return 0;
	}
}
