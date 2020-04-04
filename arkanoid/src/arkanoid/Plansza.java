package arkanoid;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import game_base_classess.GameBoard;
 
public class Plansza extends GameBoard {
       
    private int ceglyNaX=8;
    private int ceglyNaY=4;
    private int indexBonusu=0;
    
    private Plansza plansza;
    private Paletka paletka;
    private Pilka pilka;
    private Cegla[][] cegla;
    private Gracz gracz;
    private Bonus[] bonus;
               
    public Plansza(Frame okno){
    	super(new Dimension(okno.getSize()));
                   
    	plansza=this;
        okno.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent e){
	            if(getGameStatus()==Status.RESTARTED) { // game is restarted (lost a ball, going to launch new one
	                if(e.getKeyCode()==KeyEvent.VK_LEFT) {
	                    paletka.lewo();
	                    pilka.pilkaStart();
	                }
	               
	                if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
	                    paletka.prawo();
	                    pilka.pilkaStart();
	                }
	               
	                if(e.getKeyCode()==KeyEvent.VK_SPACE) {
	                    start();
	                }
	                repaint();
	            }else if(getGameStatus()==Status.ONGOING){
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){
                        paletka.lewo();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                        paletka.prawo();
                    }
                   
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pause();
                    }
                }
	        }
        });
        gracz=new Gracz(this);
        paletka=new Paletka(this, getSizeBoard().width,getSizeBoard().height-50);
        pilka =new Pilka(this,paletka);
        cegla=new Cegla[ceglyNaX][ceglyNaY];
        bonus= new Bonus[ceglyNaX*ceglyNaY*3];
      
        for(int x=0; x<ceglyNaX;x++) {
            for(int y=0;y<ceglyNaY;y++) {
                int cSzer=getSizeBoard().width/ceglyNaX;
                int cWys=40;
                cegla[x][y]=new Cegla(pilka,x*cSzer,y*cWys,cSzer,cWys);
            }
        }
           
        planszaThread.start();
    }
    
    private Thread planszaThread= new Thread(new Runnable(){
        public void run(){
            while(getGameStatus()!= Status.WIN && getGameStatus()!= Status.LOSE) {                	
            	if(getGameStatus()==Status.ONGOING) {      
            		pilka.ruch();
                    pilka.zderzenie_paletka();
                    if(!pilka.getStan()) {	
                    	strataPilki();
                    }
                    if(gracz.getIloscPilek()==0) { 
                    	lose(); 
                    }
                    
                    boolean noCegla = true;
                    
                    for(int x=0; x<ceglyNaX;x++) {
                       for(int y=0;y<ceglyNaY;y++) {
                           if(cegla[x][y].getWytrzymalosc()>0) { //if still remains Cegla
                        	   noCegla=false;
                           } 
                           cegla[x][y].zderzenie();
                           if(cegla[x][y].czyZderzenie()){
                        	   int stan;
                        	   Random r = new Random();
                        	   stan=r.nextInt(4)+1; //prawdopodobienstwo bonusu
                        	   if(stan==1){
                        		   bonus[indexBonusu]= new Bonus(cegla[x][y],paletka,plansza,pilka);
                        		   indexBonusu++;
                        	   }
                           }                                
                       }
                   }
                                           
                   for(int x=0;x<indexBonusu;x++) {
                        if(bonus[x]!=null) {
	                        bonus[x].spadek();
	                        bonus[x].zderzenie();
	                        if(bonus[x].getBonusBox().y>=getSizeBoard().height) { bonus[x].zabierz();}
	                        if(bonus[x].getAktywny()) {
	                            bonus[x].zwiekszTimer();
	                            bonus[x].wylaczBonus();
	                        }
                        }
                   }
                   gracz.zwiekszCzasGry();
                   
                   if(noCegla) { // if there's no cegla
                	   win();
                   }
                }else if(getGameStatus()!= Status.RESTARTED) {
                	
                }
            	repaint();
                try {
                       Thread.sleep(20);
                } catch (Exception e) { }	  	
            }
        }
    });    
       
    public void strataPilki(){
        restart();
        gracz.zmniejszIloscPilek();
        pilka.setStan(true);
        pilka.pilkaStart();            
    }
       
    public void paint(Graphics g){
        super.paint(g);
        g.drawRect(0, 0, getSizeBoard().width, getSizeBoard().height);
        g.setColor(new Color(51,0,255));
        g.fillRect(0, 0, getSizeBoard().width, getSizeBoard().height);
       
        gracz.rysuj(g);
        paletka.rysuj(g);
        pilka.rysuj(g);
       
        for (int x=0;x<indexBonusu;x++) {
           	if(bonus[x]!=null) {
           		bonus[x].rysuj(g);
           	}
        }
                   
        for(int x=0; x<ceglyNaX;x++) {
        	for(int y=0;y<ceglyNaY;y++) {
        		cegla[x][y].rysuj(g);
        	}
        }
                       
        if(getGameStatus()==Status.LOSE) {
            g.setFont(new Font("Arial",Font.BOLD,50));
			g.setColor(new Color(255,0,0));
			g.drawString("GAME OVER", (getSizeBoard().width/2)-158, getSizeBoard().height/2);
		}
        
		if(getGameStatus()==Status.WIN) {
			String victory="VICTORY!";
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.setColor(new Color(255,0,0));
			g.drawString(victory, (getSizeBoard().width/2)-(victory.length()*29/2), getSizeBoard().height/2);
		}
    }
}