package com.github.suknuk.learningDynamics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class CSVWriter {
	
	private PrintWriter pw;
	private StringBuilder sb;
	
	private int simulations;
	
	private int size;
	
	private int round;
	private Strategy whichAction;
	
	public CSVWriter(int simulations, int size, Strategy whichAction, Game game){
		this.simulations = simulations;
		this.size = size;
		this.round = 0;
		this.whichAction = whichAction;
		try {
			
			String filename = "results_"+ game.x + "_" + game.neighborhood + "_" + game.imitationMethod + "_" + whichAction + ".csv";
			
			pw = new PrintWriter(new File(filename));
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
	
	public void appendEntries(List<Game> myGames) {
		
		List<Integer> level = new ArrayList<Integer>();
		for (Game game:myGames){
			level.add(game.getActionLevel(whichAction));
		}
		
		
		double min = level.get(0)/(double)this.size;
		double max = level.get(0)/(double)this.size;
		double average = 0;
		double stdev;
		
        sb.append(this.round);
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
        
        this.round++;
	}
	
	public void closeFile(){
        pw.write(sb.toString());
        pw.close();
	}
}
