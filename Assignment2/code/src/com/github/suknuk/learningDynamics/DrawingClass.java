package com.github.suknuk.learningDynamics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.github.suknuk.learningDynamics.GameInfo.Strategies;
import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class DrawingClass {
	public static void createImage(Game game, String fileName, Strategies strategies){
		
		int colorOne = 0, colorTwo = 0, colorThree = 0;
		
		int scale = 5;
		
		if (strategies == Strategies.TWO) {
	        int redR = 255;
	        int redG = 0;
	        int redB = 0;
	        int redInt = (redR << 16) | (redG << 8) | redB;
	        colorOne = redInt;
			
	        int blueR = 0;
	        int blueG = 0;
	        int blueB = 255;
	        int blueInt = (blueR << 16) | (blueG << 8) | blueB;
	        colorTwo = blueInt;
		}
		else if (strategies == Strategies.THREE) {
	        int redR = 255;
	        int redG = 0;
	        int redB = 0;
	        int redInt = (redR << 16) | (redG << 8) | redB;
	        colorOne = redInt;
			
	        int blueR = 0;
	        int blueG = 0;
	        int blueB = 255;
	        int blueInt = (blueR << 16) | (blueG << 8) | blueB;
	        colorTwo = blueInt;
	        
	        int greenR = 0;
	        int greenG = 255;
	        int greenB = 0;
	        int greenInt = (greenR << 16) | (greenG << 8) | greenB;
	        colorThree = greenInt;
		}
		

		try{
			
            BufferedImage img = new BufferedImage( 
                game.x*scale, game.y*scale, BufferedImage.TYPE_INT_RGB );

            File f = new File(fileName);

            for (int x = 0; x < game.x; x++) {
            	for (int y = 0; y < game.y; y++) {
            		int color = 0;
            		if (game.map[x][y].getStrategy() == Strategy.ACTION_ONE){
            			color = colorOne;
            		} else if (game.map[x][y].getStrategy() == Strategy.ACTION_TWO){
            			color = colorTwo;
            		} else if (game.map[x][y].getStrategy() == Strategy.ACTION_THREE){
            			color = colorThree;
            		}
            		
            		for (int i = 0; i < scale; i++) {
            			for (int j = 0; j < scale; j++) {
            				img.setRGB(x*scale+i, y*scale+j, color);
            			}
            		}
            	}
            }

            ImageIO.write(img, "PNG", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
}
