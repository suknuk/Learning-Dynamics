package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.Strategies;
import com.github.suknuk.learningDynamics.GameInfo.Strategy;

import java.util.*;

public class Player {
	
	public static Random random = new Random();
	
	private int payoff;
	private Strategy strategy;
	
	public Player(Strategies strategies, List<Integer> strategyDistribution){
		this.payoff = 0;
		int randomNumber = random.nextInt(100);
		
		if (strategies == Strategies.TWO){	
			if (randomNumber >= strategyDistribution.get(0)) {
				this.strategy = Strategy.ACTION_ONE;
			} else {
				this.strategy = Strategy.ACTION_TWO;
			}
		}
		
		else if (strategies == Strategies.THREE){
			if (randomNumber >= strategyDistribution.get(0)) {
				this.strategy = Strategy.ACTION_ONE;
			} else if (randomNumber < strategyDistribution.get(0) && randomNumber >= strategyDistribution.get(1)){
				this.strategy = Strategy.ACTION_TWO;
			} else if (randomNumber < strategyDistribution.get(1)){
				this.strategy = Strategy.ACTION_THREE;
			} 
		}
	}
	
	
	public int getPayoff(){
		return this.payoff;
	}
	
	public void setPayoff(int payoff){
		this.payoff = payoff;
	}
	
	public void addPayoff(int payoff) {
		this.payoff += payoff;
	}
	
	public Strategy getStrategy(){
		return this.strategy;
	}
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
}
