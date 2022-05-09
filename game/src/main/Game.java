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

import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {
	
	Timer timer1 = new Timer(60,this); 
	private int myTimer = 0;
	private int catSpawnFreq = 100;
	private int targetSpawnFreq = 200;
	private BufferedImage chickImg, catImg,targetImg;
	private int chickX = 485;
	private int chickY = 50;
	private int chickMovement = 30;
	private ArrayList<Enemy> cats = new ArrayList<Enemy>();
	private ArrayList<Target> targets = new ArrayList<Target>();
	
	public Game(BufferedImage chickImg, BufferedImage catImage, BufferedImage targetImage) {
		this.chickImg = chickImg;
		this.catImg = catImage;
		this.targetImg = targetImage;
		timer1.start();
	}
	
	private int getRandomCatX() {
		Random generator = new Random();
		return 100 + generator.nextInt(850);
	}
	
	private int getRandomTargetX() {
		Random generator = new Random();
		int num1 = 700 + generator.nextInt(800);
		int num2 = generator.nextInt(100);
		int num3 = 0 + generator.nextInt(2);
		if (num3 == 1)
		{
			return num1;
		}
		else {
			return num2;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.chickImg, chickX, chickY, null);
		
		for(Enemy cat: cats)
		{
			g.drawImage(this.catImg, cat.getX() , cat.getY(),null);
		}
		
		for(Target tar: targets)
		{
			g.drawImage(this.targetImg, tar.getX(),tar.getY(),null);
		}
		
		
		
	}
	
	@Override
    public void repaint() {
        super.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		myTimer += 1;
		if(myTimer%catSpawnFreq == 0 )
		{
			Enemy newCat = new Enemy(getRandomCatX(),900);
			cats.add(newCat);
		}
		
		if(myTimer%targetSpawnFreq == 0 )
		{
			Target newTarget = new Target(getRandomTargetX(),900);
			targets.add(newTarget);
		}
		
		for (Enemy cat: cats) {
			if(cat.getY()<= -this.catImg.getHeight()) {
				cat = null;
			}
			else {
				cat.move();	
			}
			
		}
		
		for (Target tar: targets) {
			if(tar.getY()<= -this.targetImg.getHeight()) {
				tar = null;
			}
			else {
				tar.move();	
			}
			
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
			}
			
		}
		else if(key == KeyEvent.VK_RIGHT)
		{
			if(chickX >= 950){
				chickX = 950;
			}
			else{
             chickX += chickMovement;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
