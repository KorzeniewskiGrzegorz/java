package arkanoid;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
