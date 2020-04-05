package game_base_classess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameBoard extends JPanel {

    private Dimension sizeBoard;
    private Dimension sizeWindow;

    public GameBoard(Dimension sW, Dimension sB) {
    	sizeWindow = sW;
    	sizeBoard = sB;
    }
    
    public GameBoard(Dimension sW) { // default sizeBoard
    	sizeWindow = sW;
    	sizeBoard = new Dimension (496,600);
    }
    
    public Dimension getSizeBoard() {
        return sizeBoard;
    }
    
    public Dimension getSizeWindow() {
    	return sizeWindow;
    }
    
    public void paint(Graphics g){
	    super.paint(g);
	    g.drawRect(0, 0, sizeBoard.width, sizeBoard.height);
	    g.setColor(new Color(51,0,255));
	    g.fillRect(0, 0, sizeBoard.width, sizeBoard.height);
    }
}
