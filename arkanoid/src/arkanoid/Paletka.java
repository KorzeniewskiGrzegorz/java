package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paletka extends Rectangle {

	private static final int wysokosc =20;
	private Plansza plansza;
	public static final int SZER_WASKO=60;
	public static final int SZER_NORMALNIE=100;
	public static final int SZER_SZEROKO=160;

	public Paletka(Plansza pla,int szerokosc_planszy,int polozenie_y_na_planszy){
		super((szerokosc_planszy/2)-(SZER_NORMALNIE/2),polozenie_y_na_planszy,SZER_NORMALNIE,wysokosc);
		plansza=pla;
	}
	
	public void lewo() {
		x-=10;
		if(x<0) {
			x=0;
		}
	}
	
	public void prawo()	{
		x+=10;
		if(x>plansza.getSizeBoard().width-width) {
			x=plansza.getSizeBoard().width-width;
		}
	}
	
	public Paletka getPaletkaBox()	{
		return this;
	}

	public void setPaletkaBox(int x,int y,int szerokosc,int wysokosc){
		this.x=x;
		this.y=y;
		width=szerokosc;
		height=wysokosc;
	}
	
	public void rysuj(Graphics g){
		g.setColor(new Color(201,201,201));
		g.fillRect(x,y, width, height);
		g.setColor(new Color(0,0,0));
		g.drawRect(x,y, width, height);
		g.setColor(new Color(255,0,0));
		g.fillRect(x,y, width/10, height);
		g.fillRect(x+width*9/10,y, width/10, height);
	}
}