package main;

import java.util.Random;

public class TargetSmall {
	private int x;//X coordinate of targets
	private int y;//Y coordinate of targets
	public static int speed = 2; //The movement speed of targets in each iteration 
	
	
	public TargetSmall(int x ,int y) { // Constructor
		this.x = x;
		this.y = y;
	}
	public static int getRandomTargetX() {//This function creates random target coordinates in between specific positions
		Random generator = new Random();
		int num1 = 800 + generator.nextInt(900);
		int num2 = generator.nextInt(100);
		int num3 = generator.nextInt(2);
		if (num3 == 1)
		{
			return num1;
		}
		else {
			return num2;
		}
	}
	public int getX() { //Getters
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {//Setters
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move() { // Function to move targets
		this.y -= speed;
	}
	
	public static void setSpeed(int speed) {//Speed changes when the level changes
		Target.speed = speed;
	}
}
