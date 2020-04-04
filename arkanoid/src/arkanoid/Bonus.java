package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;



public class Bonus {

	
	private Rectangle bonusBox;

	private boolean spadajacy=false;
	private boolean aktywny=false;
	private int typ; 
	private long timer;
	public final long TIMER_LIMIT=150;
	private int predkosc=5;
		
	private Cegla cegla;
	private Paletka paletka;
	private Plansza plansza;
	private Pilka pilka;
	
	public Bonus(Cegla ceg, Paletka pal,Plansza pla,Pilka pil){
		cegla=ceg;
		paletka=pal;
		plansza=pla;
		pilka=pil;
		int bok=20;
		
		bonusBox= new Rectangle (cegla.getCeglaBox().x + (cegla.getCeglaBox().width/2)-(bok/2),
				cegla.getCeglaBox().y+ (cegla.getCeglaBox().height/2)-(bok/2),
				bok,bok);
		
		Random r = new Random();
		
		spadajacy=true;
		
		//losowanie typu
		typ=r.nextInt(4)+1; 
		
		
		
	}
	
	public Rectangle getBonusBox(){
		return bonusBox;
	}
	public void zwiekszTimer(){
		timer++;
		
	}
	
	public long getTimer(){
		return timer;
	}
	public boolean getAktywny()
	{
		return aktywny;
	}
	
	
	public void zabierz(){
		
		spadajacy=false;
	}
	
	public void spadek(){
		if(spadajacy)
		bonusBox.y+=predkosc;
	}
	public void zderzenie(){
		if(bonusBox.intersects(paletka.getPaletkaBox()) && spadajacy  )
		{
			wykonajBonus();
			aktywny=true;
			spadajacy=false;
			
		}		
	}
	
	private void wykonajBonus(){
		
		switch(typ)
		{
		//ulepszenia
		case 1: zmniejszPredPilki();
				break;
		case 2: zwiekszPaletke();
				break;
			//tutaj zaczynaja sie utrudniacze
		case 3: zwiekszPredPilki();
				break;
		case 4:	 zmniejszPaletke();
				break;
		
		default: break;
		}
		
		
	}
	
	public void wylaczBonus(){
		
		if(timer>TIMER_LIMIT)
		{
			switch(typ)
			{
			case 1: wylaczBonusPilka();
				break;
			case 2: wylaczBonusPaletka();
				break;
			/////////////////////
			case 3: wylaczBonusPilka();
				break;
			case 4:	 wylaczBonusPaletka();
				break;
			
			default: break;
			}	
			
			aktywny=false;
			
			timer=0;
		}
	}
	
	private void zwiekszPaletke()
	{
		
		
		paletka.setPaletkaBox(paletka.getPaletkaBox().x,paletka.getPaletkaBox().y,								
				paletka.SZER_SZEROKO, paletka.getPaletkaBox().height);
		
		if(paletka.getPaletkaBox().x+paletka.getPaletkaBox().width>plansza.getRozmiarPlanszy().width)
		
			paletka.setPaletkaBox(plansza.getRozmiarPlanszy().width-paletka.SZER_SZEROKO,paletka.getPaletkaBox().y,					
				paletka.SZER_SZEROKO, paletka.getPaletkaBox().height);
		
	}
	
	private void zmniejszPaletke()
	{
		
		
		paletka.setPaletkaBox(paletka.getPaletkaBox().x,
								paletka.getPaletkaBox().y,
								paletka.SZER_WASKO, paletka.getPaletkaBox().height);
		
	}
	
	private void wylaczBonusPaletka(){
		
		paletka.setPaletkaBox(paletka.getPaletkaBox().x,
				paletka.getPaletkaBox().y,
				paletka.SZER_NORMALNIE, paletka.getPaletkaBox().height);
		
		if(paletka.getPaletkaBox().x+paletka.getPaletkaBox().width>plansza.getRozmiarPlanszy().width)
			
			paletka.setPaletkaBox(plansza.getRozmiarPlanszy().width-paletka.SZER_NORMALNIE,paletka.getPaletkaBox().y,					
				paletka.SZER_NORMALNIE, paletka.getPaletkaBox().height);
		
	}
	
	
	
	private void zwiekszPredPilki(){
		pilka.setPredPilki(pilka.PRED_SZYBKO);
		
	}
	
	private void zmniejszPredPilki(){
		pilka.setPredPilki(pilka.PRED_WOLNO);
		
	}
	
	private void wylaczBonusPilka(){
		pilka.setPredPilki(pilka.PRED_NORMALNIE);
	}
	
	//public void zwiekszWytrzymalosc(){
	//	int wytrz=cegla.getWytrzymalosc();
	//	if(wytrz!=0)cegla.setWytrzymalosc(wytrz++);
	//}
	
	//public void zmniejszWytrzymalosc(){
	//	int wytrz=cegla.getWytrzymalosc();
	//	
	//	if(wytrz!=1) cegla.setWytrzymalosc(wytrz--);
		
	//}
	
	
	public void rysuj(Graphics g){
		
		if(spadajacy)
		{
			if(typ<=2)//ulepszenie
			{
				int szerPasa=6;
				
				g.setColor(new Color(51, 204, 51));
				g.fillRect(bonusBox.x,bonusBox.y,bonusBox.width,bonusBox.height);
				g.setColor(new Color(255, 255, 255));
				g.fillRect(bonusBox.x,bonusBox.y+(bonusBox.width/2)-(szerPasa/2),bonusBox.width,szerPasa);//poziomy pas
				g.fillRect(bonusBox.x+(bonusBox.width/2)-(szerPasa/2),bonusBox.y,szerPasa,bonusBox.width);//pionowy pas
				
			}
			else//utrudniacze
			{
				int szerPasa=6;
				
				g.setColor(new Color(255, 0, 0));
				g.fillRect(bonusBox.x,bonusBox.y,bonusBox.width,bonusBox.height);
				g.setColor(new Color(255, 255, 255));
				g.fillRect(bonusBox.x,bonusBox.y+(bonusBox.width/2)-(szerPasa/2),bonusBox.width,szerPasa);//poziomy pas
				
			}
		}
		
	}
	
	
}
