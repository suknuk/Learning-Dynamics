package com.github.suknuk.learningDynamics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVWriter {
	
	private PrintWriter pw;
	private StringBuilder sb;
	
	public CSVWriter(){
		try {
			pw = new PrintWriter(new File("results.csv"));
			sb = new StringBuilder();
			
	        sb.append("round");
	        sb.append(',');
	        sb.append("cooperation");
	        sb.append('\n');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void appendEntry(int round, int level) {
        sb.append(round);
        sb.append(',');
        sb.append(level);
        sb.append('\n');
	}
	
	public void closeFile(){
        pw.write(sb.toString());
        pw.close();
	}
}
