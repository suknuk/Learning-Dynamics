package com.github.suknuk.learningDynamics.Part2;

import java.util.ArrayList;

public class Estimator {

	private ArrayList<Double> values;
	private double sum;
	private int turns;
	
	public Estimator() {
		this.values = new ArrayList<Double>();
		this.sum = 0.0;
		this.turns = 0;
	}
	
	public double getAverage(){
		if (this.values.size() != 0) {
			return this.values.get(this.values.size()-1);
		} else {
			return 0.0;
		}
	}
	
	public void addTurn(double turn) {
		this.sum += turn;
		this.turns++;
		this.values.add(this.sum / this.turns);
	}
	
	public int getTurns() {
		return this.turns;
	}
}
