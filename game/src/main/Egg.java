package main;

public class Egg {
	private int x;//X coordinate of eggs
	private int y;//Y coordinate of eggs
	private int direction;//the movement direction for eggs. Either 1 or -1 
	private int speed = 4; //The movement speed of eggs in each iteration 
	
	
	public Egg(int x ,int y, int direction) { // Constructor
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int getX() { //Getters
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	public int getDir() {
		return this.direction;
	}
	
	public void setX(int x) {//Setters
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move() { // Function to move eggs
		this.x += direction*speed;
	}
	
	
}
