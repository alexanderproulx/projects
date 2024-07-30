import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gamePanel extends JPanel implements Runnable {

	static final int GAME_WIDTH = 750;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH/2);
	Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int UNIT_SIZE = 25;
	static final int BALL_DIAMETER = 20;
	static final int BUMPER_WIDTH = 100;
	static final int BUMPER_HEIGHT = 20;
	static final int BRICK_GAP = (int)(UNIT_SIZE/2);
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	int rows = 4;
	int cols = 20;
	brick[][] bricks;
	bumper bumper;
	ball ball;
	score score;
	boolean gameOver = false;
	
	gamePanel() {
		
		this.setPreferredSize(SCREEN_SIZE);
		this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(new events());
		
		newBumper(); 
        newBall();
        newBricks();
        score = new score(GAME_WIDTH, GAME_HEIGHT, UNIT_SIZE);
        
        gameThread = new Thread(this);
        gameThread.start();
		
	}
	
	public void newBall() {
		
		random = new Random();
		ball = new ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT/ 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
		
	}
	
	public void newBumper() {
		
		bumper = new bumper(GAME_WIDTH / 2 - BUMPER_WIDTH / 2, GAME_HEIGHT - 100, BUMPER_WIDTH, BUMPER_HEIGHT);
		
	}
	
	public void newBricks() {
		
		bricks = new brick[rows][cols];
		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int brickX = 10 + col * (UNIT_SIZE + BRICK_GAP);
                int brickY = 10 + row * (UNIT_SIZE + BRICK_GAP);
                bricks[row][col] = new brick(brickX, brickY, UNIT_SIZE, UNIT_SIZE);
            }
		}
		
	}
	
	public void move() {
		
		bumper.move();
		ball.move();
		
	}
	
	public void checkCollisions() {
		
		if (bumper.x <= 0) {
			bumper.x = 0;
		}
		if (bumper.x >= (GAME_WIDTH - BUMPER_WIDTH)) {
			bumper.x = GAME_WIDTH - BUMPER_WIDTH;
		}
		if (ball.x <= 0) {
			ball.setXDirection(-ball.xVelocity);
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			ball.setXDirection(-ball.xVelocity);
		}
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.intersects(bumper)) {
			ball.yVelocity = -ball.yVelocity;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            brick brick = bricks[row][col];
	            if (brick.isVisible() && ball.intersects(brick)) {
	                ball.setYDirection(-ball.yVelocity);
	                score.bricksBroken++;
	                brick.setVisible(false);
	            }
	        }
	    }
		if (ball.y >= GAME_HEIGHT - UNIT_SIZE) {
			ball.y = GAME_HEIGHT - UNIT_SIZE;
			ball.xVelocity = 0;
			gameOver = true;
		}
		
	}
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
		
	}
	
	public void draw(Graphics g) {
		
		score.draw(g, gameOver);
		bumper.draw(g);
		ball.draw(g);
		
		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                bricks[row][col].draw(g);
            }
        }
		
	}
	
	public class events extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			
			bumper.keyPressed(e);
			
		}
		public void keyReleased(KeyEvent e) {
			
			bumper.keyReleased(e);
			
		}
		
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollisions();
				repaint();
				delta--;
				
			}
		}
		
	}

}

