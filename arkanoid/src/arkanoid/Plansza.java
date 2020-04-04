package arkanoid;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Plansza extends JPanel {
       
    private Dimension rozmiarPlanszy = new Dimension (496,600);
    private Dimension rozmiarOkna;
    private int ceglyNaX=8;
    private int ceglyNaY=4;
    private boolean stanGry = false;//true - w trakcie, false - stracilismy pilke i ustawiamy paletke od nowa
    private boolean isPaused = true;
    private boolean przegrana=false;
    private boolean wygrana=false;
    private int indexBonusu=0;
    
    private Plansza plansza;
    private Paletka paletka;
    private Pilka pilka;
    private Cegla[][] cegla;
    private Gracz gracz;
    private Bonus[] bonus;
               
    public Plansza(Frame okno){
    	rozmiarOkna=new Dimension(okno.getSize());
                   
    	plansza=this;
        okno.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e){
            if(!stanGry)
            {
                if(e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                    paletka.lewo();
                    pilka.pilkaStart();
                }
               
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                {
                    paletka.prawo();
                    pilka.pilkaStart();
                }
               
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    start();
                }
                repaint();
            }
            else
            {
                if(!isPaused)
                {
                    if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    {
                        paletka.lewo();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                    {
                        paletka.prawo();
                    }
                   
                    if(e.getKeyCode()==KeyEvent.VK_SPACE)
                    {
                           
                        pause();
                    }
                }
                else
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                         start();
                }
            }
               
        }
                       
                       
            });
            gracz=new Gracz(this);
            paletka=new Paletka(this, rozmiarPlanszy.width,rozmiarPlanszy.height-50);
            pilka =new Pilka(this,paletka);
            cegla=new Cegla[ceglyNaX][ceglyNaY];
            bonus= new Bonus[ceglyNaX*ceglyNaY*3];
          
           
            for(int x=0; x<ceglyNaX;x++)
            {
                for(int y=0;y<ceglyNaY;y++)
                {
                    int cSzer=rozmiarPlanszy.width/ceglyNaX;
                    int cWys=40;
                   
                    cegla[x][y]=new Cegla(pilka,x*cSzer,y*cWys,cSzer,cWys);
                }
            }
       
               
               
            planszaThread.start();
        }
        private Thread planszaThread= new Thread(new Runnable(){
            public void run(){
                while(!(wygrana||przegrana))
                {                	
                	if(!isPaused)
                    {      
                   	 pilka.ruch();
                        pilka.zderzenie_paletka();
                        if(!pilka.getStan())strataPilki();
                        if(gracz.getIloscPilek()==0)przegrana=true;
                        
                        wygrana=true;
                        
                        for(int x=0; x<ceglyNaX;x++)
                        {
                           for(int y=0;y<ceglyNaY;y++)
                           {
                                   if(cegla[x][y].getWytrzymalosc()>0) wygrana=false;
                          
                                   cegla[x][y].zderzenie();
                                  
                                   if(cegla[x][y].czyZderzenie())
                                   {
                                	   int stan;
                                	   Random r = new Random();
                                	   stan=r.nextInt(4)+1; //prawdopodobienstwo bonusu
                                	   if(stan==1)
                                	   {
                                		   bonus[indexBonusu]= new Bonus(cegla[x][y],paletka,plansza,pilka);
                                		   indexBonusu++;
                                	   }
                                	   
                                   }                                
                               }
                         }
                        
                       for(int x=0;x<indexBonusu;x++)
                       {
	                        if(bonus[x]!=null)
	                        {
		                       
		                        bonus[x].spadek();
		                        bonus[x].zderzenie();
		                        if(bonus[x].getBonusBox().y>=rozmiarPlanszy.height) bonus[x].zabierz();
		                        if(bonus[x].getAktywny())
		                        {
		                            bonus[x].zwiekszTimer();
		                            bonus[x].wylaczBonus();
		                       
		                        }
	                        }
                       }
                        
                
                    repaint();
                    gracz.zwiekszCzasGry();
                    
                    }
                   try {
                           Thread.sleep(20);
                   } catch (Exception e) { }	  	
                }
            }
               
        });    
       
       
        public void start()
        {
	        isPaused=false;
	       
	        if(!stanGry)stanGry=true;                      
               
        }
       
        public void pause()
        {
           isPaused=true;
        }
       
        public void strataPilki()
        {
            stanGry=false;
            pause();
            gracz.zmniejszIloscPilek();
            pilka.setStan(true);
            pilka.pilkaStart();            
        }
       
        public Dimension getRozmiarPlanszy()
        {
            return rozmiarPlanszy;
        }
        public Dimension getRozmiarOkna()
        {
        	return rozmiarOkna;
        }
       
        public void paint(Graphics g){
            super.paint(g);
            g.drawRect(0, 0, rozmiarPlanszy.width, rozmiarPlanszy.height);
            g.setColor(new Color(51,0,255));
            g.fillRect(0, 0, rozmiarPlanszy.width, rozmiarPlanszy.height);
           
           
            gracz.rysuj(g);
            paletka.rysuj(g);
            pilka.rysuj(g);
           
            for (int x=0;x<indexBonusu;x++)
            {
               	if(bonus[x]!=null)
            		bonus[x].rysuj(g);
            }
                       
            for(int x=0; x<ceglyNaX;x++)
            {
            	for(int y=0;y<ceglyNaY;y++)
            	{
            		cegla[x][y].rysuj(g);
            	}
            }
                           
            if(przegrana)
            {
                g.setFont(new Font("Arial",Font.BOLD,50));
				g.setColor(new Color(255,0,0));
				g.drawString("GAME OVER", (rozmiarPlanszy.width/2)-158, rozmiarPlanszy.height/2);
			}
            
			if(wygrana)
			{
				String victory="VICTORY!";
				g.setFont(new Font("Arial",Font.BOLD,50));
				g.setColor(new Color(255,0,0));
				g.drawString(victory, (rozmiarPlanszy.width/2)-(victory.length()*29/2), rozmiarPlanszy.height/2);
			}
					           
		  }
	}