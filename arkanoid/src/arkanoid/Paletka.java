package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class Paletka  {

	private Rectangle paletkaBox;
	private int szerokosc;
	public final int SZER_WASKO=60;
	public final int SZER_NORMALNIE=100;
	public final int SZER_SZEROKO=160;
	private static final int wysokosc =20;
	private Plansza plansza;

	public Paletka(Plansza pla,int szerokosc_planszy,int polozenie_y_na_planszy){
		plansza=pla;
		szerokosc=SZER_NORMALNIE;
		
        paletkaBox=new Rectangle((szerokosc_planszy/2)-(szerokosc/2),polozenie_y_na_planszy,szerokosc,wysokosc);
        
        
	}
	
	
	
	public void lewo()
	{
		paletkaBox.x-=10;
		if(paletkaBox.x<0) paletkaBox.x=0;
		
	}
	
	public void prawo()
	{
		paletkaBox.x+=10;
		if(paletkaBox.x>plansza.getRozmiarPlanszy().width-paletkaBox.width) 
			paletkaBox.x=plansza.getRozmiarPlanszy().width-paletkaBox.width;
	}
	
	public Rectangle getPaletkaBox()
	{
		return paletkaBox;
	}

	
	public void setPaletkaBox(int x,int y,int szerokosc,int wysokosc){
		paletkaBox.x=x;
		paletkaBox.y=y;
		paletkaBox.width=szerokosc;
		paletkaBox.height=wysokosc;
		
	}
	
	public void rysuj(Graphics g){
		g.setColor(new Color(201,201,201));
		g.fillRect(paletkaBox.x,paletkaBox.y, paletkaBox.width, paletkaBox.height);
		g.setColor(new Color(0,0,0));
		g.drawRect(paletkaBox.x,paletkaBox.y, paletkaBox.width, paletkaBox.height);
		g.setColor(new Color(255,0,0));
		g.fillRect(paletkaBox.x,paletkaBox.y, paletkaBox.width/10, paletkaBox.height);
		g.fillRect(paletkaBox.x+paletkaBox.width*9/10,paletkaBox.y, paletkaBox.width/10, paletkaBox.height);
		
	}
}