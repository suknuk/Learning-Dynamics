package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;

public class Game {

	int x;
	int y;
	
	Player[][] map;
	
	GameInfo gameInfo;
	Neighborhood neighborhood;
	
	public Game(int x, int y, GameType gameType, Neighborhood neighborhood) {
		this.x = x;
		this.y = y;
		this.gameInfo = new GameInfo(gameType);
		this.neighborhood = neighborhood;
		
		this.map = new Player[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				this.map[i][j] = new Player();
			}
		}
	}
	
	public void playRounds(int rounds){
		for (int i = 0; i < rounds; i++) {
			singlePlayerGame();
		}
	}
	
	private void singlePlayerGame(){
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				map[i][j].setPayoff(0);
				
				// Von Neumann
				map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i-1,x)][j]));
				map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i+1,x)][j]));
				map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[i][realPosition(j-1,y)]));
				map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[i][realPosition(j+1,y)]));
				
				if (this.neighborhood == Neighborhood.MOORE) {
					map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i-1,x)][realPosition(j-1,y)]));
					map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i+1,x)][realPosition(j-1,y)]));
					map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i-1,x)][realPosition(j+1,y)]));
					map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], map[realPosition(i+1,x)][realPosition(j+1,y)]));
				} 
			}
		}
	}
	
	private int realPosition(int position, int mapSize){
		if (position < 0) {
			return mapSize - 1;
		} else if (position >= mapSize) {
			return 0;
		} else {
			return position;
		}
	}
}
