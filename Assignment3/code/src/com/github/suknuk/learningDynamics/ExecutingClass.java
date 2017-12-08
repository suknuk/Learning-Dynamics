package com.github.suknuk.learningDynamics;

public class ExecutingClass {

	public static void main(String[] args) {

		CSVWriter csvw = new CSVWriter("softmaxtime.csv");

		int howManyGames = 2000;
		int rounds = 1000;

		double[] values = { 1.3, 0.9, 1.1, 0.6, 0.5, 0.4, 0.3, 2 };

		double[] totalAverage = new double[rounds];
		double[][][] averageQ = new double[4][rounds][3];

		Game[] game = new Game[howManyGames];
		for (int games = 0; games < howManyGames; games++) {
			game[games] = new Game(values, Game.Method.SOFTMAX_TIME, 0.1);
		}

		for (int round = 0; round < rounds; round++) {
			// first, do simulation in every simultaneous game 
			for (int games = 0; games < howManyGames; games++) {
				game[games].playRounds(1);
				
				// Total average reward of all simultaneous games 
				totalAverage[round] += (game[games].getTotalReward() / howManyGames) / (round + 1);
			}

			// now we have results!
			// iterate every arm j
			for (int j = 0; j < 4; j++) {
				double mean = 0.0;
				double[] allResults = new double[howManyGames];
				
				for(int games = 0; games < howManyGames; games++){
					mean += game[games].bandits.get(j).getCurrentAvg() / howManyGames;
					allResults[games] = game[games].bandits.get(j).getCurrentAvg();
					
					// # times arm was picked
					averageQ[j][round][2] += (double)game[games].bandits.get(j).getValues().size() / howManyGames;
				}
				
				//variance
				double temp = 0;
				for(double a : allResults)
					temp += (a - mean) * (a - mean);
				temp = temp / (howManyGames - 1);
				
				double stdev = Math.sqrt(temp);
				
				averageQ[j][round][0] = mean;
				averageQ[j][round][1] = stdev;
				
			}
			
			//totalAverage = game[games].bandits.
		}

		// averageReward[i] += game.getAverageReward() / howManyGames;

		csvw.appendEntries(averageQ, totalAverage);
		csvw.closeFile();

	}
}
