package com.github.suknuk.learningDynamics;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	public static Random r = new Random();
	
	public static enum Method{
		RANDOM, eGREEDY, SOFTMAX,eGREEDY_TIME, SOFTMAX_TIME
	}
	
	Method gameMethod;
	double parameter;
	ArrayList<BanditArm> bandits;
	int playedRounds;
	
	
	public Game(double[] banditVals, Method method, double parameter){
		this.gameMethod = method;
		this.bandits = new ArrayList<BanditArm>();
		this.parameter = parameter;
		this.playedRounds = 0;
		for(int i = 0; i < banditVals.length; i+=2){
			this.bandits.add(new BanditArm(banditVals[i],banditVals[i+1]));
		}
	}
	
	public void playRounds(int rounds){
		for(int i = 0; i < rounds; i++){
			
			this.playedRounds ++;
			
			// RANDOM
			if (this.gameMethod == Method.RANDOM) {
				this.bandits.get(r.nextInt(this.bandits.size())).turn();
			}
			// e-Greedy
			else if(this.gameMethod == Method.eGREEDY){
				
				// Exploit best arm
				if (r.nextDouble() > this.parameter) {
					
					ArrayList<BanditArm> bestArms = getBestArms();
					bestArms.get(r.nextInt(bestArms.size())).turn();
					
				// Explore
				} else {
					this.bandits.get(r.nextInt(this.bandits.size())).turn();
				}
				
			}
			// SOFTMAX and time_softmax
			else if(this.gameMethod == Method.SOFTMAX || this.gameMethod == Method.SOFTMAX_TIME) {
				
				ArrayList<Double> expQs = new ArrayList<Double>();
				double expQs_sum = 0.0;
				
				// Distribution from 0.0 to 1.0
				double[] pqs = new double[this.bandits.size()+1];
				pqs[0] = 0.0;
				
				// Calculate top and bottom values
				for (BanditArm ba : this.bandits) {
					double expQ = 0.0;
					if (this.gameMethod == Method.SOFTMAX) {
						expQ = Math.exp(ba.getCurrentAvg()/this.parameter);
					} else {
						//expQ = Math.exp(ba.getCurrentAvg()/ (4*(1000-this.playedRounds)/1000));
						expQ = Math.exp(ba.getCurrentAvg()/ (4 - (this.playedRounds)/250));
					}
					expQs.add(expQ);
					expQs_sum += expQ;
				}
				
				double p_sum = 0.0;
				// calculate P for every expQ
				for (int j = 0; j < expQs.size(); j++) {
					double pVal = expQs.get(j) / expQs_sum;
					p_sum += pVal;
					pqs[j+1] = p_sum;
				}
				
				double rndVal = Math.random();
				for (int j = 0; j < 5; j++) {
					if (rndVal >= pqs[j] && rndVal < pqs[j+1]) {
						this.bandits.get(j).turn();
						//System.out.println("Choose arm " + (j+1));
						break;
					}
				}
			}
			// eGreedy with parameter 1/sqrt(t)
			else if(this.gameMethod == Method.eGREEDY_TIME) {
				
				// Exploit best arm
				if (r.nextDouble() > (1/Math.sqrt(this.playedRounds))) {
					
					ArrayList<BanditArm> bestArms = getBestArms();
					bestArms.get(r.nextInt(bestArms.size())).turn();
					
				// Explore
				} else {
					this.bandits.get(r.nextInt(this.bandits.size())).turn();
				}
				
			}
			
		}
	}
	
	private ArrayList<BanditArm> getBestArms(){
		
		// Initialize with first arm as default
		ArrayList<BanditArm> bestArms = new ArrayList<BanditArm>();
		bestArms.add(this.bandits.get(0));
		double bestArmVal = this.bandits.get(0).getCurrentAvg();
		
		for (int i = 1; i < this.bandits.size(); i++) {
			
			// found new best arm
			if (this.bandits.get(i).getCurrentAvg() > bestArmVal) {
				bestArms = new ArrayList<BanditArm>();
				bestArms.add(this.bandits.get(i));
				bestArmVal = this.bandits.get(i).getCurrentAvg();
				
			// arm has equal average
			} else if(this.bandits.get(i).getCurrentAvg() == bestArmVal) {
				bestArms.add(this.bandits.get(i));
			}
		}
		
		return bestArms;
	}
	
	public void getGameInfo(){
		for(BanditArm bandit : this.bandits) {
			System.out.println("pulled : " + bandit.values.size() + " average: " + bandit.getCurrentAvg());
		}
		System.out.println("Total reward: " + this.getTotalReward());
		System.out.println("Possible total reward : " + this.maxReward());
		System.out.println("Regret : " + (this.maxReward()-this.getTotalReward()));
	}
	
	public double getTotalReward(){
		double tmp = 0;
		for(BanditArm arm:this.bandits){
			tmp += arm.getCurrentAvg() * arm.getValues().size();
		}
		return tmp;
	}
	
	public double getAverageReward(){
		return this.getTotalReward()/((double)this.playedRounds);
	}
	
	public double maxReward(){
		return this.playedRounds * this.bandits.get(0).mean;
	}
}
