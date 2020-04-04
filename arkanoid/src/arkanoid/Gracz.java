package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Time;

import javax.swing.JTextArea;

public class Gracz {

	private Plansza plansza;
	private Dimension rozmiarGracza;
	private String nazwa="Grzegorz";
	private String sZycia="Ilosc pilek ";
	private int poczUkl;
	private int iloscPilek=3;
	private long czasGryImpulsy;
	private long czasGrySekundy=0;
	private long czasGryMinuty=0;
	
	
	public Gracz(Plansza pla){
		plansza=pla;
		rozmiarGracza=new Dimension (plansza.getRozmiarOkna().
				width,plansza.getRozmiarOkna().height-plansza.getRozmiarPlanszy().height);
		poczUkl=plansza.getRozmiarPlanszy().height;
		
	}
	
	public void zmniejszIloscPilek()
	{
		iloscPilek--;
	}
	
	public int getIloscPilek(){
		return iloscPilek;
	}
	
	public void zwiekszCzasGry()
	{
		czasGryImpulsy++;
		czasGrySekundy=czasGryImpulsy/17;
		if(czasGrySekundy>=60)
		{
			czasGryMinuty++;
			czasGryImpulsy=0;
		}
	}
	
	public void rysuj(Graphics g){
		
		g.setFont(new Font("Arial",Font.BOLD,25));
		g.setColor(new Color(0, 0, 153));
		
		g.fillRect(0, poczUkl,rozmiarGracza.width, 
				rozmiarGracza.height);
		
		g.setColor(new Color(255,0,0));
		g.drawString(nazwa,30,poczUkl+30);
		
		g.drawString(sZycia,30,poczUkl+65);
		
		for(int i=0;i<iloscPilek;i++ )
		{
			g.setColor(new Color(240,240,240));
			g.fillOval(30 + i*30, poczUkl+100, 30, 30);
			g.setColor(new Color(0,0,0));
			g.drawOval(30 + i*30, poczUkl+100, 30, 30);
		}
		
		g.setColor(new Color(255,0,0));
		g.drawString("czas gry:",300,poczUkl+65);
		if(czasGrySekundy<10) g.drawString(String.valueOf(czasGryMinuty)+":0"+String.valueOf(czasGrySekundy),300,poczUkl+100);
		else
		     g.drawString(String.valueOf(czasGryMinuty)+":"+String.valueOf(czasGrySekundy),300,poczUkl+100);
		
	}
	
}
