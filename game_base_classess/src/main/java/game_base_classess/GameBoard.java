package game_base_classess;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GameBoard extends JPanel {

    private Dimension sizeBoard;
    private Dimension sizeWindow;
    public enum Status {
        WIN,
        LOSE,
        PAUSED,
        ONGOING,
        RESTARTED
      }
    Status gameStatus;
    
    public GameBoard(Dimension sW, Dimension sB) {
    	gameStatus= Status.RESTARTED;
    	sizeWindow = sW;
    	sizeBoard = sB;
    }
    
    public GameBoard(Dimension sW) { // default sizeBoard
    	gameStatus= Status.RESTARTED;
    	sizeWindow = sW;
    	sizeBoard = new Dimension (496,600);
    }
    
    public Dimension getSizeBoard() {
        return sizeBoard;
    }
    
    public Dimension getSizeWindow() {
    	return sizeWindow;
    }
    
    public Status getGameStatus() {
    	return gameStatus;
    }

    public void start(){
    	gameStatus = Status.ONGOING;
    }
   
    public void pause(){
    	gameStatus = Status.PAUSED;
    }
    
    public void win(){
    	gameStatus = Status.WIN;
    }
    
    public void lose(){
    	gameStatus = Status.LOSE;
    }
    
    public void restart(){
    	gameStatus = Status.RESTARTED;
    }
    
}
