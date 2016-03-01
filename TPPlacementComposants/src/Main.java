import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;


public class Main {
	
	public static void Question1main(String[] args){
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		JWindow w = new JWindow();
		JLabel l = new JLabel("Hello");
	
		w.getContentPane().add(l);
		w.setLocation(width/2-100, height/2-100);
		w.setSize(width, height);
		w.setVisible(true);
		
		
	}
	
	public static void Question5main(String[] args){
		JFrame f = new JFrame();
		JLabel l = new JLabel("Hello World ! WeshWeshWesh");
		
		
		f.getContentPane().add(l);
		f.setSize(500, 200);
		//f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(f);
		f.setVisible(true);
		
		JDialog d1 = new JDialog(f);
		JDialog d2 = new JDialog(f);
		
		d1.setTitle("non modale");
		d1.setSize(200,200);
		d2.setTitle("modal");
		d2.setSize(150, 150);
		d2.setModal(true);
		d1.setLocationRelativeTo(f);
		d2.setLocationRelativeTo(f);
		d1.setVisible(true);
		d2.setVisible(true);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void Question6main(String[] args){
		JFrame f = new JFrame();
		JButton nord = new JButton("Nord");
		JButton centre = new JButton("Centre");
		JButton sud = new JButton("Sud");
		JButton est = new JButton("Est");
		JButton ouest = new JButton("Ouest");
		
		nord.setSize(500, 50);
		sud.setSize(500, 50);
		centre.setSize(400,400);
		est.setSize(50,400);
		ouest.setSize(50,400);
		
		f.getContentPane().add(nord, BorderLayout.NORTH);
		f.getContentPane().add(centre, BorderLayout.CENTER);
		f.getContentPane().add(sud, BorderLayout.SOUTH);
		f.getContentPane().add(est, BorderLayout.EAST);
		f.getContentPane().add(ouest, BorderLayout.WEST);
		
		f.setSize(500, 500);
		//f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void Question7main(String[] args){
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		for (int i = 1; i<=16;i++){
			f.getContentPane().add(new JButton("Bouton "+i));
		}
		
		f.setSize(400, 300);
		//f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new GridLayout(0,4,2,2));
		
		for (int i = 1; i<=16;i++){
			f.getContentPane().add(new JButton("Bouton "+i));
		}
		
		f.setSize(500, 500);
		//f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
