import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class score {

	int bricksBroken;
	int GAME_WIDTH;
	int GAME_HEIGHT;
	int UNIT_SIZE;
	
	score(int GAME_WIDTH, int GAME_HEIGHT, int UNIT_SIZE) {
		
		this.GAME_WIDTH = GAME_WIDTH;
		this.GAME_HEIGHT = GAME_HEIGHT;
		this.UNIT_SIZE = UNIT_SIZE;
		
	}
	
	public void draw(Graphics g, boolean gameOver) {
		
		if (!gameOver) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Consolas", Font.PLAIN, UNIT_SIZE * 2));
			g.drawString(String.valueOf(bricksBroken), GAME_WIDTH / 2 - UNIT_SIZE, GAME_HEIGHT - UNIT_SIZE);
		}
		if (gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Consolas", Font.PLAIN, UNIT_SIZE * 2));
			g.drawString("Game Over", GAME_WIDTH / 2 - UNIT_SIZE, GAME_HEIGHT - UNIT_SIZE);
		}
		
	}
	
}