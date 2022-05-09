package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {
	
	Timer timer1 = new Timer(30,this); 
	private BufferedImage chick, cat;
	private int chickX = 485;
	private int chickY = 280;
	private int chickMovement = 30;
	private ArrayList<Enemy> cats = new ArrayList<Enemy>();
	
	public Game(BufferedImage chickImg, BufferedImage catImage) {
		this.chick = chickImg;
		this.cat = catImage;
		timer1.start();
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(chick, chickX, chickY, null);
		
		for(Enemy cat: cats)
		{
			g.drawImage(this.cat, cat.getX() , cat.getY(),null);
		}
		
		
		
	}
	
	@Override
    public void repaint() {
        super.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Enemy newCat = new Enemy(500,900);
		cats.add(newCat);
		
		for (Enemy cat: cats) {
			cat.move();
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT)
		{
			if(chickX <= 0){
				chickX = 0;
			}
			else{
             chickX -= chickMovement;     
             System.out.println(chickX);
			}
			
		}
		else if(key == KeyEvent.VK_RIGHT)
		{
			if(chickX >= 950){
				chickX = 950;
			}
			else{
             chickX += chickMovement;
             System.out.println(chickX);
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
