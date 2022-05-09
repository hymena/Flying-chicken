package main;

public class Enemy {

	private int x;//X coordinate of cats'
	private int y;//Y coordinate of cats'
	private int speed = 10; //The movement speed of cats in each iteration 
	
	
	public Enemy(int x ,int y) { // Constructor
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
	
	public void move() { // Function to move cats
		this.y -= speed;
	}
}
