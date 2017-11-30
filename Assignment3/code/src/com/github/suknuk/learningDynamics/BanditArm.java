package com.github.suknuk.learningDynamics;

import java.util.ArrayList;
import java.util.Random;

public class BanditArm implements BanditArmInterface{
	
	public static Random r = new Random();
	
	ArrayList<Double> values; // averages
	double mean;
	double stdev;
	
	public BanditArm(double mean, double stdev) {
		this.values = new ArrayList<Double>();
		this.mean = mean;
		this.stdev = stdev;
	}
	
	public double getCurrentAvg(){
		if (this.values.size() != 0) {
			return this.values.get(this.values.size()-1);
		} else {
			return 0;
		}
	}
	
	@Override
	public void turn() {
		double newReward = r.nextGaussian()*this.stdev+this.mean;
		
		// no average yet
		if (this.values.size() == 0) {
			this.values.add(newReward);
			
		// Add new average
		// last avg * (size) + (new reward)   / new size
		} else {
			double lastAvg = this.values.get(this.values.size()-1);
			double size = this.values.size();
			double newAvg = (lastAvg*size + newReward) / (size+1);
			this.values.add(newAvg);
		}
	}
	
	public ArrayList<Double> getValues(){
		return this.values;
	}
}
