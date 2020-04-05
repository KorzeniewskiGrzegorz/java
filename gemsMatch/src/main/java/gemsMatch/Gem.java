package gemsMatch;

import java.awt.Rectangle;
import java.util.Random;

public class Gem extends Rectangle {
	private enum Type{
		RED,
		BLUE,
		GREEN;
	}
	private Type type;
	
	public Gem(int x,int y,int width, int heigth ) {
		super(x,y,width,heigth);
		
		type = Type.BLUE;
	}
}
