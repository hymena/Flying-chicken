package main;

import java.util.Random;

public class Cat {

	private int x;//X coordinate of cats'
	private int y;//Y coordinate of cats'
	public static int speed = 2; //The movement speed of cats in each iteration 
	
	
	public Cat(int x ,int y) { // Constructor
		this.x = x;
		this.y = y;
	}
	
	public static int getRandomCatX() {//This function creates random cat coordinates in between specific positions
		Random generator = new Random();
		return 150 + generator.nextInt(810);
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
	
	public void move() { // Function to move cats
		this.y -= speed;
	}
	
	public static void setSpeed(int speed) {//Speed changes when the level changes
		Cat.speed = speed;
	}
}
