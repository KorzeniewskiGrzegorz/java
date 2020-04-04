package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pilka {

	private int x;
	private int y;
	private int predkosc;
	private int srednica;
	private boolean stan=true;;
	
	public final int PRED_WOLNO=3;
	public final int PRED_NORMALNIE=5;
	public final int PRED_SZYBKO=10;
	
	private boolean kier_x;//true - prawo , false - lewo
	private boolean kier_y;//true - dol , false - gora
	private boolean kier_pion;
	
	private Plansza plansza;
	private Paletka paletka;
	
	public Pilka(Plansza pla,Paletka palet){
		kier_x=true;
		kier_y=false;
		kier_pion=false;
		plansza=pla;
		paletka=palet;
		srednica=17;
		predkosc=PRED_NORMALNIE; // 5 - wolno, 10 - normalnie, 15- szybko
		
		
		x=paletka.getPaletkaBox().x+(paletka.getPaletkaBox().width/2)-(srednica/2);
		y=paletka.getPaletkaBox().y-srednica;
		
		
	}
	
	public void ruch()
	{
		//this.zderzenie_paletka();
		
		if(x+1<=0 ) kier_x=true;
		if(x+srednica+1>=plansza.getSizeBoard().width) kier_x=false;
		if(y+1<=0)kier_y=true;
		
		if(!kier_pion)
		{
			if(kier_x&&kier_y)//prawo, dol
			{
				x+=predkosc;
				y+=predkosc;
	
			}
			
			if(kier_x&&!kier_y)//prawo, gora
			{
				
				x+=predkosc;
				y-=predkosc;
	
			}
	
			if(!kier_x&&kier_y)//lewo,dol
			{
				
				x-=predkosc;
				y+=predkosc;
	
			}
	
			if(!kier_x&&!kier_y)//lewo,gora
			{
	
				x-=predkosc;
				y-=predkosc;
			}
		}
		else
		{
			if(kier_x&&kier_y)//prawo, dol
			{
				x+=predkosc/4;
				y+=predkosc+predkosc/2;
	
			}
			
			if(kier_x&&!kier_y)//prawo, gora
			{
				
				x+=predkosc/4;
				y-=predkosc+predkosc/2;
	
			}
	
			if(!kier_x&&kier_y)//lewo,dol
			{
				
				x-=predkosc/4;
				y+=predkosc+predkosc/2;
	
			}
	
			if(!kier_x&&!kier_y)//lewo,gora
			{
	
				x-=predkosc/4;
				y-=predkosc+predkosc/2;
			}
		}
				
		if(y>=plansza.getSizeBoard().height-srednica)stan=false;
		
		
	}
	
	public void zderzenie_paletka()
	{
		if(x+srednica>=paletka.getPaletkaBox().x && x<=paletka.getPaletkaBox().x+(paletka.getPaletkaBox().width/3) 
				&& y<=paletka.getPaletkaBox().y
				&& y+srednica>=paletka.getPaletkaBox().y
				)
		{
			kier_y=false;
			kier_x=false;
			kier_pion=false;
		}
		
		if(x>=paletka.getPaletkaBox().x+(paletka.getPaletkaBox().width/3)
				&& x<=paletka.getPaletkaBox().x+(2*paletka.getPaletkaBox().width/3) 
				&& y<=paletka.getPaletkaBox().y
				&& y+srednica>=paletka.getPaletkaBox().y)
		{
			kier_y=false;
			kier_pion=true;
			
		}
			
		if(x>=paletka.getPaletkaBox().x+(2*paletka.getPaletkaBox().width/3)
				&& x<=paletka.getPaletkaBox().x+(3*paletka.getPaletkaBox().width/3) 
				&& y<=paletka.getPaletkaBox().y
				&& y+srednica>=paletka.getPaletkaBox().y)
		{	
			kier_y=false;
			kier_x=true;
			kier_pion=false;
		}
		
		
	}
	
	public void odwrocX(){
		if(kier_x) kier_x=false;
				else kier_x=true;
	}
	
	public void odwrocY(){
		if(kier_y) kier_y=false;
				else kier_y=true;
	}
	
	public void odwrocWPrawo(){
		//if(kier_x) kier_x=false;
		//else kier_x=true;
		kier_x=true;
	}
	public void odwrocWLewo(){
		//if(kier_x) kier_x=false;
		//else kier_x=true;
		kier_x=false;
	}
	
	public void odwrocWDol(){
		//if(kier_y) kier_y=false;
		//else kier_y=true;
		kier_y=true;
	}
	
	public void odwrocWGore(){
		//if(kier_y) kier_y=false;
		//else kier_y=true;
		kier_y=false;
	}
	
	public int getPilkaX(){
		return this.x;
	}
	
	public int getPilkaY(){
		return this.y;
	}
	
	public int getPilkaSrednica(){
		return this.srednica;
	}
	public boolean getKierY(){
		return kier_y;
	}
	public boolean getKierX(){
		return kier_x;
	}
	
	public boolean getStan(){
		return stan;
	}
	
	public void setStan(boolean wartosc){
		 stan=wartosc;
	}
	
	public void setPredPilki(int predkosc){
		
		this.predkosc=predkosc;
	}
	
	public void pilkaStart(){
		this.x=paletka.getPaletkaBox().x+(paletka.getPaletkaBox().width/2)-(srednica/2);
		this.y=(paletka.getPaletkaBox().y-srednica);
		
		kier_x=true;
		kier_y=false;
	}
	
	
	
	public void rysuj(Graphics g){
		
		g.setColor(new Color(240,240,240));
		g.fillOval(x, y, srednica, srednica);
		g.setColor(new Color(0,0,0));
		g.drawOval(x, y, srednica, srednica);
		
		
		
	}

	
	
}



