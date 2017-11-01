package com.github.suknuk.learningDynamics;

import com.github.suknuk.learningDynamics.GameInfo.Strategy;

import java.util.*;

public class Player {
	
	public static Random random = new Random();
	
	private int payoff;
	private Strategy strategy;
	
	public Player(){
		this.payoff = 0;
		int randomNumber = random.nextInt(100);
		if (randomNumber > 50) {
			this.strategy = Strategy.ACTION_ONE;
		} else {
			this.strategy = Strategy.ACTION_TWO;
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
