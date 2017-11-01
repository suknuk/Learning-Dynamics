package com.github.suknuk.learningDynamics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriter {
	
	private PrintWriter pw;
	private StringBuilder sb;
	
	private int simulations;
	
	public CSVWriter(int simulations){
		this.simulations = simulations;
		try {
			pw = new PrintWriter(new File("results.csv"));
			sb = new StringBuilder();
			
	        sb.append("round");
	        
	        for (int i = 0; i < this.simulations; i++) {
		        sb.append(',');
		        sb.append("cooperation"+i);
	        }
	        
	        sb.append('\n');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void appendEntries(int round, List<Integer> level) {
        sb.append(round);
        for (Integer i:level) {
            sb.append(',');
            sb.append(i);
        }
        sb.append('\n');
	}
	
	public void closeFile(){
        pw.write(sb.toString());
        pw.close();
	}
}
