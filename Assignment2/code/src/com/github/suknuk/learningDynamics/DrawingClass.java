package com.github.suknuk.learningDynamics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.github.suknuk.learningDynamics.GameInfo.Strategy;

public class DrawingClass {
	public static void createImage(Game game){
		
		int scale = 5;
		
        int blueR = 0;
        int blueG = 0;
        int blueB = 255;
        int blueInt = (blueR << 16) | (blueG << 8) | blueB;
        
        int redR = 255;
        int redG = 0;
        int redB = 0;
        int redInt = (redR << 16) | (redG << 8) | redB;
		
		try{
			
            BufferedImage img = new BufferedImage( 
                game.x*scale, game.y*scale, BufferedImage.TYPE_INT_RGB );

            File f = new File("MyFile.png");

            for (int x = 0; x < game.x; x++) {
            	for (int y = 0; y < game.y; y++) {
            		int color = 0;
            		if (game.map[x][y].getStrategy() == Strategy.ACTION_ONE){
            			color = blueInt;
            		} else if (game.map[x][y].getStrategy() == Strategy.ACTION_TWO){
            			color = redInt;
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
