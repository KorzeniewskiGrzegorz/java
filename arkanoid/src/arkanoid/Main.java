package arkanoid;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static JFrame okno;
	public static Plansza plansza;
	
	public Main(){
		
	 this.setTitle("Arkanoid");
	 this.setSize(503, 800);
	 this.setPreferredSize(getSize());
	 this.setResizable(false);
	 this.setLocationRelativeTo(null);
	 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 this.setVisible(true);
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		okno = new Main();
		plansza= new Plansza(okno);
		okno.add(plansza);
		okno.pack();
	}
}
