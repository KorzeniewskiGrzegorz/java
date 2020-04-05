package gemsMatch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import game_base_classess.GamePlayer;

public class Player extends GamePlayer {
	private String stringMoves;
	
	public Player(String playerName,int moves) {
		super(playerName,moves);
		stringMoves = "Moves left: ";
	}

	public void paint(Graphics g,Point anchor ){
		g.setColor(new Color(255,0,0));
		g.drawString(getName(),30,anchor.y+30);
		g.drawString(stringMoves + getLifeCount(),30,anchor.y+65);
	}
}
