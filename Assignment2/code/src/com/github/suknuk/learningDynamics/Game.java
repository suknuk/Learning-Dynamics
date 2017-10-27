package com.github.suknuk.learningDynamics;

import java.util.ArrayList;
import java.util.Random;

import com.github.suknuk.learningDynamics.GameInfo.GameType;
import com.github.suknuk.learningDynamics.GameInfo.ImitationMethod;
import com.github.suknuk.learningDynamics.GameInfo.Neighborhood;

public class Game {

	public static Random random = new Random();
	
	int x;
	int y;
	
	int playedRounds;
	
	Player[][] map;
	
	GameInfo gameInfo;
	Neighborhood neighborhood;
	ImitationMethod imitationMethod;
	
	public Game(int x, int y, GameType gameType, Neighborhood neighborhood, ImitationMethod imitationMethod) {
		this.x = x;
		this.y = y;
		this.playedRounds = 0;
		this.gameInfo = new GameInfo(gameType);
		this.neighborhood = neighborhood;
		this.imitationMethod = imitationMethod;
		
		this.map = new Player[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				this.map[i][j] = new Player();
			}
		}
	}
	
	public void playRounds(int rounds){
		for (int i = 0; i < rounds; i++) {
			this.playedRounds++;
			playSingleGame();
			doImitation();
		}
	}
	
	private void doImitation(){
		
		Player[][] tmpMap = new Player[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				tmpMap[i][j] = new Player();
			}
		}
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				ArrayList<Player> neighbors = this.getNeighbors(i, j);
					
				if (this.imitationMethod == ImitationMethod.HIGHEST_EARNER) {
					Player maxEarner = map[i][j];
					for (Player p : neighbors) {
						if (p.getPayoff() > maxEarner.getPayoff()) {
							maxEarner = p;
						}
					}
					tmpMap[i][j].setStrategy(maxEarner.getStrategy());
				}
				else if (this.imitationMethod == ImitationMethod.REPLICATOR_RULE) {
					int randomPlayer = random.nextInt(neighbors.size());
					
					double Wi = map[i][j].getPayoff();
					double Wj = neighbors.get(randomPlayer).getPayoff();
					double N = neighbors.size();
					double max = Math.max(gameInfo.P, Math.max(gameInfo.R, Math.max(gameInfo.T, gameInfo.S)));
					double min = Math.min(gameInfo.P, Math.min(gameInfo.R, Math.min(gameInfo.T, gameInfo.S)));
					
					double Pij = (1 + (Wj-Wi)/(N*(max-min))) / 2;
					
					System.out.println(Wi + " " + Wj + " " + N + " " + max + " " + min);
					System.out.println(Pij);
					
					
				}
			}
		}
		
		this.map = tmpMap;
	}
	
	private ArrayList<Player> getNeighbors(int px, int py) {
		ArrayList<Player> list = new ArrayList<Player>();
		
		if (this.neighborhood == Neighborhood.MOORE || this.neighborhood == Neighborhood.VON_NEUMANN) {
			list.add(map[realPosition(px-1,x)][py]);
			list.add(map[realPosition(px+1,x)][py]);
			list.add(map[px][realPosition(py-1,y)]);
			list.add(map[px][realPosition(py+1,y)]);
		}

		if (this.neighborhood == Neighborhood.MOORE) {
			list.add(map[realPosition(px-1,x)][realPosition(py-1,y)]);
			list.add(map[realPosition(px+1,x)][realPosition(py-1,y)]);
			list.add(map[realPosition(px-1,x)][realPosition(py+1,y)]);
			list.add(map[realPosition(px+1,x)][realPosition(py+1,y)]);
		}
		
		return list;
	}
	
	private void playSingleGame(){
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				map[i][j].setPayoff(0);
				
				for (Player p: this.getNeighbors(i, j)){
					map[i][j].addPayoff(this.gameInfo.calculatePayoff(map[i][j], p));
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
