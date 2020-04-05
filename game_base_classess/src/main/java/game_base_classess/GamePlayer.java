package game_base_classess;

public class GamePlayer {
	private StopWatch gameTime;
	private String name;

	private int lifeCount;
    public enum Status {
        WIN,
        LOSE,
        PAUSED,
        ONGOING,
        RESTARTED
      }
    Status gameStatus;
	
	public GamePlayer(String nam, int l) {
    	gameStatus= Status.RESTARTED;
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
    
    public String getName() { //seconds
    	return name;
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
	
	public static void main(String[] args) {
		GamePlayer gP = new GamePlayer("Grzegorz",3);
		
		gP.getGameTime();
	}
}
