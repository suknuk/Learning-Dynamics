package com.github.suknuk.learningDynamics.Part2;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

	public enum algo {
		BOLTZMANN, OPTIMISTIC_BOLTZMANN
	}

	// [myAction][OpponentAction]
	private Estimator[][] qvals;
	private int[] oppositeActions = { 1, 1, 1 };
	private int[][] actionMatrix = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	private algo whatAlgo;
	private double parameter;
	private boolean doPrint;

	public Player(algo algo, double parameter) {
		this.qvals = new Estimator[3][3];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				qvals[y][x] = new Estimator();
			}
		}
		this.whatAlgo = algo;
		this.parameter = parameter;
		this.doPrint = false;
	}

	public Player(algo algo, double parameter, boolean doPrint) {
		this(algo, parameter);
		this.doPrint = doPrint;
	}

	public int getAction() {

		if (this.whatAlgo == Player.algo.BOLTZMANN || this.whatAlgo == Player.algo.OPTIMISTIC_BOLTZMANN) {

			// get list of all estimator values per action
			// probability of each action of opponent is in oppositeActions
			int totalActions = Arrays.stream(oppositeActions).sum();
			if (totalActions == 0) {
				totalActions = 1;
			}

			double[] estimates = new double[] { 0, 0, 0 };
			// boltzmann action selection
			if (this.whatAlgo == Player.algo.BOLTZMANN) {
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						double p = ((double) oppositeActions[x]) / totalActions;
						estimates[y] += p * this.qvals[y][x].getAverage();
						this.print(oppositeActions[x] + ", " + totalActions);
						this.print("new estimate " + y + " : " + estimates[y] + " with p=" + p);
					}
				}
			}
			// optimistic boltzmann
			else if (this.whatAlgo == Player.algo.OPTIMISTIC_BOLTZMANN) {
				for (int y = 0; y < 3; y++) {
					double max = -100;
					double avg;
					for (int x = 0; x < 3; x++) {

						// has not been turnes -> give high value so it will be
						// explored
						if (this.qvals[y][x].getTurns() == 0) {
							avg = 15;
						} else {
							avg = this.qvals[y][x].getAverage();
						}

						if (avg > max) {
							max = avg;
						}
					}
					estimates[y] = max;
					this.print("believes " + y + " is the max with: " + max);
				}
			}

			this.print("estimates: " + Arrays.toString(estimates));

			// now select action based on an algo

			double[] exp_vals = new double[3];
			double exp_sum = 0;

			for (int i = 0; i < 3; i++) {
				// Boltzmann selection
				if (this.whatAlgo == Player.algo.BOLTZMANN) {
					exp_vals[i] = Math.exp(estimates[i] / this.parameter);
				}
				// Optimistic boltzmann selection
				else if (this.whatAlgo == Player.algo.OPTIMISTIC_BOLTZMANN) {
					exp_vals[i] = Math.exp(estimates[i] / this.parameter);
				}
				exp_sum += exp_vals[i];
			}

			this.print("exp_vals: " + Arrays.toString(exp_vals));

			double[] p = new double[4];
			double p_sum = 0;
			p[0] = 0.0;

			for (int i = 0; i < 3; i++) {
				p_sum += exp_vals[i] / exp_sum;
				p[i + 1] = p_sum;
			}

			this.print("p dist: " + Arrays.toString(p));

			double rndVal = Math.random();
			int pickedAction = -1;
			for (int j = 0; j < 4; j++) {
				if (rndVal >= p[j] && rndVal < p[j + 1]) {
					this.print("random :" + rndVal + " , chose: " + j);
					pickedAction = j;
					break;
				}
			}

			if (pickedAction == -1) {
				this.print("should not happen - picking 2 because no distributioon val was found");
				pickedAction = 2;
			}

			return pickedAction;
		}

		return 0;
	}

	public void addActionAndResult(int myAction, int opponentAction, double reward) {
		this.oppositeActions[opponentAction] += 1;
		this.actionMatrix[myAction][opponentAction] += 1;
		this.qvals[myAction][opponentAction].addTurn(reward);
	}

	private void print(String s) {
		if (this.doPrint) {
			System.out.println(s);
		}
	}

	public void printActionMatrix() {
		System.out.println(Arrays.toString(this.actionMatrix[0]));
		System.out.println(Arrays.toString(this.actionMatrix[1]));
		System.out.println(Arrays.toString(this.actionMatrix[2]));
	}

}
