import java.awt.*;
import java.awt.event.KeyEvent;

public class bumper extends Rectangle {
	
	int xVelocity;
	int speed = 10;
	
	bumper(int x, int y, int BUMPER_WIDTH, int BUMPER_HEIGHT) {
		
		super(x, y, BUMPER_WIDTH, BUMPER_HEIGHT);
		
	}
	
	public void move() {
		
		x += xVelocity;
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			setXDirection(-speed);
			move();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			setXDirection(speed);
			move();
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			setXDirection(0);
			move();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			setXDirection(0);
			move();
		}
		
	}
	
	public void setXDirection(int xDirection) {
		
		xVelocity = xDirection;
		
	}
	
	public void draw (Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		
	}
	
	
}
