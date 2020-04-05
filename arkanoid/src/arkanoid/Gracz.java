package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import game_base_classess.GamePlayer;

public class Gracz extends GamePlayer {

	private String sZycia="Ilosc pilek ";

	public Gracz(Plansza pla){
		super(new Dimension (pla.getSizeWindow().width,pla.getSizeWindow().height-pla.getSizeBoard().height),
				pla.getSizeBoard().height,
				"Grzegorz",
				3);
	}
	
	public void rysuj(Graphics g){
		super.paint(g);
		g.drawString(sZycia,30,getCoordPlayerArea()+65);
		
		for(int i=0;i<getLifeCount();i++ ) {
			g.setColor(new Color(240,240,240));
			g.fillOval(30 + i*30, getCoordPlayerArea()+100, 30, 30);
			g.setColor(new Color(0,0,0));
			g.drawOval(30 + i*30, getCoordPlayerArea()+100, 30, 30);
		}
		
		g.setColor(new Color(255,0,0));
		g.drawString("czas gry:",300,getCoordPlayerArea()+65);
			
		long gameTimesec = getGameTime();
		
		String formattedTime = String.format("%02d:%02d",  (gameTimesec % 3600) / 60, (gameTimesec % 60));
		g.drawString(String.valueOf(formattedTime),300,getCoordPlayerArea()+100);
		
		//g.drawString(String.valueOf(getGameTime()),300,getCoordPlayerArea()+100);
		//if(czasGrySekundy<10) g.drawString(String.valueOf(czasGryMinuty)+":0"+String.valueOf(czasGrySekundy),300,poczUkl+100);
		//else
		//     g.drawString(String.valueOf(czasGryMinuty)+":"+String.valueOf(czasGrySekundy),300,poczUkl+100);
		
	}
	
}
