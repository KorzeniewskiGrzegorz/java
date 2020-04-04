package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Cegla {
	
	private Rectangle ceglaBox;
	
	private int wytrzymalosc=3;
	private Pilka pilka;
	
	
	public Cegla(Pilka pil,int x,int y,int szer, int wys)
	{
		pilka=pil;
		ceglaBox=new Rectangle(x,y,szer,wys);
				
	}
	

	public Rectangle getCeglaBox(){
		return ceglaBox;
	}
	
	public int getWytrzymalosc(){
		return wytrzymalosc;
	}
	
	public void setWytrzymalosc(int wytrz){
		 wytrzymalosc=wytrz;
	}
	
	public void zderzenie()
	{		
		if(	ceglaBox.intersects(pilka.getPilkaX(),pilka.getPilkaY(),pilka.getPilkaSrednica(),pilka.getPilkaSrednica())		
			&& wytrzymalosc>0) 							
		{
			wytrzymalosc--;
					
			if(pilka.getKierX()&&pilka.getKierY())//prawo, dol
			{
				
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=ceglaBox.x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=ceglaBox.x+ceglaBox.width
						&& pilka.getPilkaY()+pilka.getPilkaSrednica()>=ceglaBox.y)//gorna krawedz
				
				pilka.odwrocWGore();
				else pilka.odwrocWLewo();
					
			}
			if(!pilka.getKierX()&&pilka.getKierY())//lewo, dol
			{
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=ceglaBox.x &&
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=ceglaBox.x+ceglaBox.width
						&& pilka.getPilkaY()+pilka.getPilkaSrednica()>=ceglaBox.y)//gorna krawedz
				pilka.odwrocWGore();
				else pilka.odwrocWPrawo();	
			}
			
			if(!pilka.getKierX()&&!pilka.getKierY())//lewo,gora
			{
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=ceglaBox.x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=ceglaBox.x+ceglaBox.width
						&& pilka.getPilkaY()<=ceglaBox.y+ceglaBox.height)//dolna krawedz
				pilka.odwrocWDol();
				else pilka.odwrocWPrawo();
			}
			if(pilka.getKierX()&&!pilka.getKierY())//prawo,dol
			{
			
				if(pilka.getPilkaX()+(pilka.getPilkaSrednica()/2)>=ceglaBox.x && 
						pilka.getPilkaX()-(pilka.getPilkaSrednica()/2)<=ceglaBox.x+ceglaBox.width
						&& pilka.getPilkaY()<=ceglaBox.y+ceglaBox.height )//dolna krawedz
				pilka.odwrocWDol();
				else	pilka.odwrocWLewo();
				
			}				
		}
			
	}
	
	public boolean czyZderzenie()
	{		
		if(	ceglaBox.intersects(pilka.getPilkaX(),pilka.getPilkaY(),pilka.getPilkaSrednica(),pilka.getPilkaSrednica())		
			&& wytrzymalosc>0) 							
		{
			return true;
		}
		else return false;
	}
	
	
public void rysuj(Graphics g){
				
		if(wytrzymalosc>0)
		{
			if(wytrzymalosc==3)		g.setColor(new Color(255, 0, 0));
			if(wytrzymalosc==2)		g.setColor(new Color(255, 151, 0));
			if(wytrzymalosc==1)		g.setColor(new Color(255, 255, 0));
				
			g.fillRect(ceglaBox.x,ceglaBox.y, ceglaBox.width, ceglaBox.height);
			g.setColor(new Color(0,0,0));
			g.drawRect(ceglaBox.x,ceglaBox.y, ceglaBox.width, ceglaBox.height);
		
		}
	}
}
