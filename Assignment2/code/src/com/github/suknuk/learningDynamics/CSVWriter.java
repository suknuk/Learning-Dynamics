package com.github.suknuk.learningDynamics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriter {
	
	private PrintWriter pw;
	private StringBuilder sb;
	
	private int simulations;
	
	private int size;
	
	public CSVWriter(int simulations, int size){
		this.simulations = simulations;
		this.size = size;
		try {
			pw = new PrintWriter(new File("results.csv"));
			sb = new StringBuilder();
			
	        sb.append("round");
	        
	        for (int i = 0; i < this.simulations; i++) {
		        sb.append(',');
		        sb.append("cooperation"+i);
	        }
	        
	        sb.append(',');
	        sb.append(',');
	        sb.append("min");
	        sb.append(',');
	        sb.append("max");
	        sb.append(',');
	        sb.append("average");
	        sb.append(',');
	        sb.append("stdev");        

	        
	        sb.append('\n');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void appendEntries(int round, List<Integer> level) {
		
		double min = level.get(0)/(double)this.size;
		double max = level.get(0)/(double)this.size;
		double average = 0;
		double stdev;
		
        sb.append(round);
        for (Integer i:level) {
        	double fraction = ((double)i)/((double)this.size);
        	if (fraction < min) {
        		min = fraction;
        	}
        	if (fraction > max) {
        		max = fraction;
        	}
        	average += fraction;
            sb.append(',');
            sb.append(String.format("%.4f", fraction));
        }
        
        //mean /average
        average = average / ((double)level.size());
        
        //stdev
        double tmp = 0;
        for (Integer i: level){
        	double j = ((double)i)/(double)this.size;
        	tmp += (j-average)*(j-average);
        }
        tmp = tmp/(level.size()-1);
        
        stdev = Math.sqrt(tmp);
        
        sb.append(',');
        sb.append(',');
        sb.append(String.format("%.4f", min));
        sb.append(',');
        sb.append(String.format("%.4f", max));
        sb.append(',');
        sb.append(String.format("%.4f", average));
        sb.append(',');
        sb.append(String.format("%.4f", stdev));
        
        sb.append('\n');
	}
	
	public void closeFile(){
        pw.write(sb.toString());
        pw.close();
	}
}
