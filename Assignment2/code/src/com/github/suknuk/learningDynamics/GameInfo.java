package com.github.suknuk.learningDynamics;

public class GameInfo {
	public static enum Strategy {
		ACTION_ONE, ACTION_TWO, ACTION_THREE;
	}
	
	public static enum Strategies {
		TWO, THREE;
	}
	
	public static enum GameType {
		PRISONERS_DILEMMA, SNOWDRIFT, ROCK_PAPER_SCISSORS;
	}
	
	public static enum Neighborhood {
		MOORE, VON_NEUMANN;
	}
	
	public static enum ImitationMethod {
		HIGHEST_EARNER, REPLICATOR_RULE;
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
	
	public int calculatePayoff(Player p1, Player p2, Strategies strategies){
		if (strategies == Strategies.TWO) {
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
		} 
		
		else if (strategies == Strategies.THREE) {
			if (p1.getStrategy() == Strategy.ACTION_ONE) {
				if (p2.getStrategy() == Strategy.ACTION_ONE) {
					return 0;
				} else if (p2.getStrategy() == Strategy.ACTION_TWO) {
					return -1;
				} else if (p2.getStrategy() == Strategy.ACTION_THREE) {
					return 1;
				}
			} else if (p1.getStrategy() == Strategy.ACTION_TWO) {
				if (p2.getStrategy() == Strategy.ACTION_ONE) {
					return 1;
				} else if (p2.getStrategy() == Strategy.ACTION_TWO) {
					return 0;
				} else if (p2.getStrategy() == Strategy.ACTION_THREE) {
					return -1;
				}
			} else if (p1.getStrategy() == Strategy.ACTION_THREE) {
				if (p2.getStrategy() == Strategy.ACTION_ONE) {
					return -1;
				} else if (p2.getStrategy() == Strategy.ACTION_TWO) {
					return 1;
				} else if (p2.getStrategy() == Strategy.ACTION_THREE) {
					return 0;
				}
			}	
		} 
		return 0;
	}
}
