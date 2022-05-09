package main;

public class Target {
	private int x;//X coordinate of targets
	private int y;//Y coordinate of targets
	private int speed = 2; //The movement speed of targets in each iteration 
	
	
	public Target(int x ,int y) { // Constructor
		this.x = x;
		this.y = y;
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
	
	public void setSpeed(int speed) {//Speed changes when the level changes
		this.speed = speed;
	}
}
