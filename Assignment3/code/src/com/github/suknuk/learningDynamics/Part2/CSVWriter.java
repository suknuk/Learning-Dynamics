package com.github.suknuk.learningDynamics.Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVWriter {

	private PrintWriter pw;
	private StringBuilder sb;

	public CSVWriter(String filename) {
		try {

			pw = new PrintWriter(new File(filename));
			sb = new StringBuilder();

			sb.append("boltzmann");
			sb.append('\n');

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// armAverages has avg + stdev [armAvg, stdev, #picked]
	// totalAvg
	public void appendEntries(double[] totalAverageBoltz) {
		for (int i = 0; i < totalAverageBoltz.length; i++) {
			sb.append(totalAverageBoltz[i]);
			sb.append('\n');
		}
	}

	public void closeFile() {
		pw.write(sb.toString());
		pw.close();
	}

}
