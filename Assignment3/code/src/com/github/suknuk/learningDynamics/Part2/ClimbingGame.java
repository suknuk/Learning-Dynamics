package com.github.suknuk.learningDynamics.Part2;

import java.util.Random;

public class ClimbingGame {

	private static Random r = new Random();

	private int[][] mean = new int[][] { { 11, -30, 0 }, { -30, 7, 6 }, { 0, 0, 5 } };
	private double[][] std;

	public ClimbingGame(double std, double std1, double std0) {
		this.std = new double[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (x == 0 && y == 0) {
					this.std[y][x] = std0;
				} else if (x == 1 && y == 1) {
					this.std[y][x] = std1;
				} else {
					this.std[y][x] = std;
				}
			}
		}
	}

	public double getReward(int a, int b) {
		return ClimbingGame.r.nextGaussian() * this.std[a][b] + this.mean[a][b];
	}

}
