package arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import game_base_classess.GamePlayer;

public class Gracz extends GamePlayer {

	private String sZycia="Ilosc pilek ";

	public Gracz(){
		super("Grzegorz",3);
	}
	
	public void rysuj(Graphics g,Point anchor ){
		
		for(int i=0;i<getLifeCount();i++ ) {
			g.setColor(new Color(240,240,240));
			g.fillOval(30 + i*30, anchor.y+100, 30, 30);
			g.setColor(new Color(0,0,0));
			g.drawOval(30 + i*30, anchor.y+100, 30, 30);
		}
		
		g.setColor(new Color(255,0,0));
		g.drawString("czas gry:",300,anchor.y+65);
			
		long gameTimesec = getGameTime();
		String formattedTime = String.format("%02d:%02d",  (gameTimesec % 3600) / 60, (gameTimesec % 60));
		g.drawString(String.valueOf(formattedTime),300,anchor.y+100);
		
		g.drawString(getName(),30,anchor.y+30);
		g.drawString(sZycia,30,anchor.y+65);
	}
}
