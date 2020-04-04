package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cegla extends Rectangle {
	
	private int wytrzymalosc=3;
	private Pilka pilka;
	
	public Cegla(Pilka pil,int x,int y,int szer, int wys) {
		super(x,y,szer,wys);
		pilka=pil;
	}

	public Cegla getCeglaBox(){
		return this;
	}
	
	public int getWytrzymalosc(){
		return wytrzymalosc;
	}
	
	public void setWytrzymalosc(int wytrz){
		 wytrzymalosc=wytrz;
	}
	
	public void zderzenie() {		
		if(	intersects(pilka.getPilkaX(),pilka.getPilkaY(),pilka.getPilkaSrednica(),pilka.getPilkaSrednica())		
			&& wytrzymalosc>0) {
			wytrzymalosc--;
					
			if(pilka.getKierX()&&pilka.getKierY()){//prawo, dol
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=x+width
						&& pilka.getPilkaY()+pilka.getPilkaSrednica()>=y) {//gorna krawedz
					pilka.odwrocWGore();		
				}else {
					pilka.odwrocWLewo();
				}
			}
			if(!pilka.getKierX()&&pilka.getKierY()) {//lewo, dol
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=x &&
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=x+width
						&& pilka.getPilkaY()+pilka.getPilkaSrednica()>=y) {//gorna krawedz
					pilka.odwrocWGore();
				}else {
					pilka.odwrocWPrawo();	
				}
			}
			if(!pilka.getKierX()&&!pilka.getKierY()){//lewo,gora
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=x+width
						&& pilka.getPilkaY()<=y+height) {//dolna krawedz
					pilka.odwrocWDol();
				}else {
					pilka.odwrocWPrawo();
				}
			}
			if(pilka.getKierX()&&!pilka.getKierY()){//prawo,dol
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=x+width
						&& pilka.getPilkaY()<=y+height ) {//dolna krawedz
					pilka.odwrocWDol();
				}else {
					pilka.odwrocWLewo();
				}
			}				
		}
	}
	
	public boolean czyZderzenie(){		
		if(	intersects(pilka.getPilkaX(),pilka.getPilkaY(),pilka.getPilkaSrednica(),pilka.getPilkaSrednica())		
			&& wytrzymalosc>0){
			return true;
		}else {
			return false;
		}
	}
	
public void rysuj(Graphics g){
				
		if(wytrzymalosc>0){
			if(wytrzymalosc==3)		g.setColor(new Color(255, 0, 0));
			if(wytrzymalosc==2)		g.setColor(new Color(255, 151, 0));
			if(wytrzymalosc==1)		g.setColor(new Color(255, 255, 0));
				
			g.fillRect(x,y, width, height);
			g.setColor(new Color(0,0,0));
			g.drawRect(x,y, width, height);
		}
	}
}
