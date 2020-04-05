package game_base_classess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class GameBoard extends JPanel {

    private Dimension sizeBoard;
    private Color bgColorGame;
    private Color bgColorPlayerArea;
    private Point anchorGame;
    private Point anchorPlayerArea;
	private Dimension sizePlayerArea;

    public GameBoard(Dimension sW,Dimension sB, Point anchG, Point anchPl) { // default sizeBoard
    	sizeBoard = sB;
    	bgColorGame = new Color(51,0,255);
    	bgColorPlayerArea = new Color(0, 0, 153);
    	anchorGame = anchG;
    	anchorPlayerArea = anchPl;
    	sizePlayerArea = new Dimension(sizeBoard.width,
    			sW.height - sizeBoard.height);
    }
    
    public GameBoard(Dimension sW, Dimension sB) {
    	sizeBoard = sB;
    	bgColorGame = new Color(51,0,255);
    	bgColorPlayerArea = new Color(0, 0, 153);
    	anchorGame = new Point(0,0);
    	anchorPlayerArea = new Point(0,600);
    	sizePlayerArea = new Dimension(sizeBoard.width,
    			sW.height - sizeBoard.height);
    }
    
    public GameBoard(Dimension sW) { // default sizeBoard
    	sizeBoard = new Dimension (sW.width,600);
    	bgColorGame = new Color(51,0,255);
    	bgColorPlayerArea = new Color(0, 0, 153);
    	anchorGame = new Point(0,0);
    	anchorPlayerArea = new Point(0,600);
    	sizePlayerArea = new Dimension(sizeBoard.width,
    			sW.height - sizeBoard.height);
    }
    
    public Dimension getSizeBoard() {
        return sizeBoard;
    }
    
    public Color getBgColor() {
    	return bgColorGame;
    }
    
    public void setBgColor(Color color) {
    	bgColorGame = color;
    }
    
    public void setPlayerAreaColor(Color color) {
    	bgColorPlayerArea = color;
    }
    
    public Point getAnchorPlayerArea() {
    	return anchorPlayerArea;
    }
    
    public void paint(Graphics g){
	    super.paint(g);
	    g.drawRect(anchorGame.x, anchorGame.y, sizeBoard.width, sizeBoard.height);
	    g.setColor(bgColorGame);
	    g.fillRect(anchorGame.x, anchorGame.y, sizeBoard.width, sizeBoard.height);
	    
	    //PLAYER INFO AREA
		g.setFont(new Font("Arial",Font.BOLD,25));
		g.setColor(bgColorPlayerArea);
		g.fillRect(anchorPlayerArea.x, anchorPlayerArea.y,sizePlayerArea.width, 
				sizePlayerArea.height);
    }
}
