package com.github.suknuk.learningDynamics.Part2;

public class ExecutingClass {

	public static void main(String[] args) {
		// actionA : y
		// actionB : x

		int rounds = 5000;
		double std0 = 4;
		double std1 = 0.1;
		double std = 0.1;

		int simulations = 100;

		Player.algo strategy = Player.algo.BOLTZMANN;
		double temperature = 1;

		double[] totalAverage = new double[rounds];

		for (int sim = 0; sim < simulations; sim++) {
			ClimbingGame cg = new ClimbingGame(std, std0, std1);

			Player a = new Player(strategy, temperature, false);
			Player b = new Player(strategy, temperature);

			//double rewardSum = 0;

			for (int i = 0; i < rounds; i++) {
				int actionA = a.getAction();
				int actionB = b.getAction();

				double reward = cg.getReward(actionA, actionB);

				//rewardSum += reward;
				//totalAverage[i] += (rewardSum / (i + 1)) / simulations;
				totalAverage[i] += reward / simulations;

				a.addActionAndResult(actionA, actionB, reward);
				b.addActionAndResult(actionB, actionA, reward);
				//System.out.println(actionA + " " + actionB + " : " + reward);
			}
			//a.printActionMatrix();
		}

		CSVWriter writer = new CSVWriter("boltzmann_b.csv");
		writer.appendEntries(totalAverage);
		writer.closeFile();

	}

}
