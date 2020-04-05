package gemsMatch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

import game_base_classess.GameBoard;

public class Board extends GameBoard{
	private Player player;
	
    public Board(Frame okno){
    	super(okno.getSize() , //  window size
    		  new Dimension(okno.getWidth(),okno.getHeight()-100),	//board size
    			new Point(0,100),
    			new Point(0,0));
    	
    	setBgColor(new Color(255,204,153));
    	setPlayerAreaColor(new Color(255,153,76));
    	
    	player = new Player("Grzegorz",25);
	}
    
    public void paint(Graphics g){
    	super.paint(g);
    	player.paint(g,getAnchorPlayerArea());
    }
}
