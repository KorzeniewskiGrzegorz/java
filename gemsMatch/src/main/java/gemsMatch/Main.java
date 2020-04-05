package gemsMatch;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static JFrame window;
	public static Board board;
	
	public Main(){
		
		 this.setTitle("Gems Match");
		 this.setSize(500, 600);
		 this.setPreferredSize(getSize());
		 this.setResizable(false);
		 this.setLocationRelativeTo(null);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		window = new Main();
		board= new Board(window);
		window.add(board);
		window.pack();
	}
}