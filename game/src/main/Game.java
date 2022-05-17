package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	Timer timer1 = new Timer(10,this); 
	private int myTimer = 0;//This variable is stored for deploying targets and cats
	private int catSpawnFreq = 100;//Cat deploying frequency
	private int targetSpawnFreq = 200;//Target deploying frequency
	private BufferedImage chickImg, catImg,targetImg,targetSmallImg, eggRight,eggLeft, background;//The images of game
	private int chickX = 485;//Chick's x coordinate
	private final int  chickY = 50;//Chick's y coordinate. Chick can't move along the y axis
	private int chickMovement = 30;//The speed of chick for key press
	private ArrayList<Cat> cats = new ArrayList<Cat>();//Lists of cats
	private ArrayList<Target> targets = new ArrayList<Target>();//List of targets
	private ArrayList<TargetSmall> smallTargets= new ArrayList<TargetSmall>();//List of small targets 
	private ArrayList<Egg> eggs= new ArrayList<Egg>();//List of eggs
	private boolean isGameStarted = false; //Variable to start game 
	private int score = 0; //Variable to keep track of player's score
	private int level = 1; //Variable to keep track of level
	private boolean isGameOver = false;
	
	//Constructor of game 
	public Game(BufferedImage chickImg, BufferedImage catImage, BufferedImage targetImage, BufferedImage targetSmallImage,BufferedImage eggRight,BufferedImage eggLeft, BufferedImage background) {
		this.chickImg = chickImg;
		this.catImg = catImage;
		this.targetImg = targetImage;
		this.targetSmallImg = targetSmallImage;
		this.eggRight = eggRight;
		this.eggLeft = eggLeft;
		this.background = background;
		timer1.start();
	}
	
		
	public boolean collisionWithChick() {//This function checks the collisions between chick and each cat
		for(Cat cat: cats) {
			Rectangle chickRect = new Rectangle(chickX, chickY, chickImg.getWidth(), chickImg.getHeight());
			Rectangle catRect = new Rectangle(cat.getX(),cat.getY(),catImg.getWidth(),catImg.getHeight());
			if(chickRect.intersects(catRect)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionWithEgg() {
		for(Egg egg: eggs) {
			Rectangle eggRect = new Rectangle(egg.getX(),egg.getY(),this.eggLeft.getWidth(),this.eggLeft.getHeight());
			for(Target tar: targets) {
				Rectangle tarRectangle = new Rectangle(tar.getX(),tar.getY(),this.targetImg.getWidth(),this.targetImg.getHeight());
				if(eggRect.intersects(tarRectangle)) {
					targets.remove(tar);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean collisionWithEgg2() {
		for(Egg egg: eggs) {
			Rectangle eggRect = new Rectangle(egg.getX(),egg.getY(),this.eggLeft.getWidth(),this.eggLeft.getHeight());
			for(TargetSmall tar: smallTargets) {
				Rectangle tarRectangle = new Rectangle(tar.getX(),tar.getY(),this.targetImg.getWidth(),this.targetImg.getHeight());
				if(eggRect.intersects(tarRectangle)) {
					smallTargets.remove(tar);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public void paint(Graphics g) {//Paints the objects in each game loop
		super.paint(g);
		g.drawImage(this.background, 0, 0, null);//First draw background so it is behind everything
		Font font = new Font("TimesRoman", Font.PLAIN,20); 
        g.setFont(font);
        g.setColor(Color.WHITE);
		if(!isGameStarted) {
			g.drawString("How To Play?", 420, 150);
			g.drawString("Move the chick to the right and left with the arrow keys", 220, 180);
			g.drawString("Lunch eggs to the left with the key q", 320, 210);
			g.drawString("Lunch eggs to the right with the key e", 315, 240);
			g.drawString("Hit the targets and get points", 340, 270);
			g.drawString("Small targets worth 30 points", 340, 300);
			g.drawString("Big targets worth 20 points", 340, 330);
			g.drawString("And of course don't get caught by the cats!", 300, 360);
			g.drawString("Press s to start game.", 360, 390);
			g.drawString(" Have fun!", 420, 420);
		}
		else {
			
			g.drawImage(this.chickImg, chickX, chickY, null);//draw chick
			
			for(Cat cat: cats)//Draw cats
			{
				g.drawImage(this.catImg, cat.getX() , cat.getY(),null);
			}
			for(Target tar: targets)//Draw targets
			{
				g.drawImage(this.targetImg, tar.getX(),tar.getY(),null);
			}
			
			for(TargetSmall tar: smallTargets)//Draw targets
			{
				g.drawImage(this.targetSmallImg, tar.getX(),tar.getY(),null);
			}
			for(Egg egg: eggs) {//Draw eggs
				if(egg.getDir() == 1) {
					g.drawImage(this.eggRight, egg.getX(), egg.getY(),null);	
				}
				else {
					g.drawImage(this.eggLeft, egg.getX(), egg.getY(),null);	
				}
				
			}
			g.drawString("Score: " + score, 880, 50);//Draw score and level
			g.drawString("Level: " + level, 880, 80);
			
			if(collisionWithChick() == true) { //Check collision between cats and chick  
				//If collision happens write game overs
				isGameOver = true;
		        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		        g.drawString("GAME OVER !!!",300,450);
		        g.drawString("TOTAL SCORE = " + score ,250,500);
		        g.setColor(Color.BLACK);
		        g.drawString("Pres ESC to quit", 280, 550);
		        timer1.stop();  
		            
			}
			
		}
		
	}
	            	
	@Override
    public void repaint() {
        super.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(isGameStarted) {

			myTimer += 1;
			if(myTimer%catSpawnFreq == 0 )
			{
				Cat newCat = new Cat(Cat.getRandomCatX(),900);
				cats.add(newCat);
			}
			
			if(myTimer%targetSpawnFreq == 0 )
			{
				Target newTarget = new Target(Target.getRandomTargetX(),900);
				targets.add(newTarget);
				TargetSmall newTarget2 = new TargetSmall(TargetSmall.getRandomTargetX(),900);
				smallTargets.add(newTarget2);
				
			}
			
			for (int i=0;i<cats.size();i++) {
				if(cats.get(i).getY()<= -this.catImg.getHeight()) {
					Cat toNull = cats.get(i);
					toNull = null;
					cats.remove(i);
				}
				else {
					cats.get(i).move();
				}
				
			}
			
			for (int i=0; i<targets.size();i++) {
				if(targets.get(i).getY()<= -this.targetImg.getHeight()) {
					Target toNull = targets.get(i);
					toNull = null;
					targets.remove(i);
				}
				else {
					targets.get(i).move();	
				}
				
			}
			for (int i=0; i<smallTargets.size();i++) {
				if(smallTargets.get(i).getY()<= -this.targetImg.getHeight()) {
					TargetSmall toNull = smallTargets.get(i);
					toNull = null;
					smallTargets.remove(i);
				}
				else {
					smallTargets.get(i).move();	
				}
				
			}
			
			for(Egg egg: eggs) {
				if(egg.getX() <= -this.eggLeft.getWidth() || egg.getX() >= 1000 + this.eggLeft.getWidth()) {
					egg = null;
				}
				else {
					egg.move();
				}
			}
			if(collisionWithEgg() == true) {
				score += 20;
			}
			if(collisionWithEgg2() == true) {
				score+=30;
			}
			
			if(score > 100) {
				level = 2;
				Cat.setSpeed(4);
				Target.setSpeed(2);
			}
			if(score > 200)
			{
				level = 3;
				Cat.setSpeed(6);
				Target.setSpeed(4);
				catSpawnFreq =50;
				targetSpawnFreq = 100;
			}
			
			repaint();
			if(myTimer == 1000)
				myTimer = 0;
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!isGameStarted && key==KeyEvent.VK_S)
		{
			isGameStarted = true;
		}
		if(isGameOver && key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
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
		
		if(key == KeyEvent.VK_Q) {
			//Create new egg
			Egg newEgg = new Egg(this.chickX, this.chickY + (this.chickImg.getHeight())/2, -1 );
			eggs.add(newEgg);
		}
		if(key == KeyEvent.VK_E) {
			//Create new egg
			Egg newEgg = new Egg(this.chickX + this.chickImg.getWidth(), this.chickY + (this.chickImg.getHeight())/2, 1 );
			eggs.add(newEgg);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}
