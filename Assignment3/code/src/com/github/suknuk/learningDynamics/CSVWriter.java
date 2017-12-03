package com.github.suknuk.learningDynamics;

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

			sb.append("Combined average reward");
			sb.append(',');
			sb.append("Arm 1");
			sb.append(',');
			sb.append("stdev");
			sb.append(',');
			sb.append("Picked Arm 1");
			sb.append(',');
			sb.append("Arm 2");
			sb.append(',');
			sb.append("stdev");
			sb.append(',');
			sb.append("Picked Arm 2");
			sb.append(',');
			sb.append("Arm 3");
			sb.append(',');
			sb.append("stdev");
			sb.append(',');
			sb.append("Picked Arm 3");
			sb.append(',');
			sb.append("Arm 4");
			sb.append(',');
			sb.append("stdev");
			sb.append(',');
			sb.append("Picked Arm 4");
			sb.append('\n');

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// armAverages has avg + stdev [armAvg, stdev, #picked]
	// totalAvg
	public void appendEntries(double[][][] armAverages, double[] totalAverage) {
		for (int i = 0; i < armAverages[0].length; i++) {
			sb.append(totalAverage[i]);
			sb.append(',');
			for(int j = 0; j < 4; j++) {
				sb.append(armAverages[j][i][0]);
				sb.append(',');
				sb.append(armAverages[j][i][1]);
				sb.append(',');
				sb.append(armAverages[j][i][2]);
				if (j!=3) {
					sb.append(',');
				}
			}
			
			sb.append('\n');
		}
	}

	public void closeFile() {
		pw.write(sb.toString());
		pw.close();
	}
}
