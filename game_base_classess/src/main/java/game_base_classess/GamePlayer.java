package game_base_classess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class GamePlayer {
	private StopWatch gameTime;
	private String name;
	private Dimension sizePlayerArea;
	private int coordPlayerArea;//point in the window where the player area begins
	private int lifeCount;
    public enum Status {
        WIN,
        LOSE,
        PAUSED,
        ONGOING,
        RESTARTED
      }
    Status gameStatus;
	
	public GamePlayer(Dimension playerArea,int coord,String nam, int l) {
    	gameStatus= Status.RESTARTED;
		sizePlayerArea=playerArea;
		coordPlayerArea = coord;
		name=nam;
		lifeCount =l;
		gameTime = new StopWatch();
	}
	
    public Status getGameStatus() {
    	return gameStatus;
    }
    
    public long getGameTime() { //seconds
    	return gameTime.getElapsedTime();
    }

    public void start(){
    	gameStatus = Status.ONGOING;
    	gameTime.start();
    }
   
    public void pause(){
    	gameStatus = Status.PAUSED;
    	gameTime.stop();
    }
    
    public void win(){
    	gameStatus = Status.WIN;
    	gameTime.stop();
    }
    
    public void lose(){
    	gameStatus = Status.LOSE;
    	gameTime.stop();
    }
    
    public void restart(){
    	gameStatus = Status.RESTARTED;
    }
	
    public int getLifeCount() {
    	return lifeCount;
    }
    
    public void increaseLifeCount() {
    	lifeCount++;
    }
    
    public void decreaseLifeCount() {
    	lifeCount--;
    }
    
	public int getCoordPlayerArea() {
		return coordPlayerArea;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("Arial",Font.BOLD,25));
		g.setColor(new Color(0, 0, 153));
		g.fillRect(0, coordPlayerArea,sizePlayerArea.width, 
				sizePlayerArea.height);
		g.setColor(new Color(255,0,0));
		g.drawString(name,30,coordPlayerArea+30);
	}
	
	public static void main(String[] args) {
		GamePlayer gP = new GamePlayer(new Dimension (600,400),
				400,
				"Grzegorz",
				3);
		
		gP.getGameTime();
	}
}
