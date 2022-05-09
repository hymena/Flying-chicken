package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameScreen extends JFrame{
	
	private Game game;
	private BufferedImage chick, cat, target;
	
	public GameScreen(){
		importImg();
		
		setTitle("Flying Chicken");
		setSize(1000,1000);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		game= new Game(chick,cat,target);
		game.requestFocus();
		game.addKeyListener(game);
		game.setFocusable(true);
		add(game);
		
		
	}
	
	private void importImg() {
		
		InputStream is = getClass().getResourceAsStream("/chick.png");
		try {
			chick = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		is = getClass().getResourceAsStream("/cat.png");
		try {
			cat = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		is = getClass().getResourceAsStream("/target.png");
		try {
			target = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args) {
		GameScreen gameScreen = new GameScreen();
	}

}
