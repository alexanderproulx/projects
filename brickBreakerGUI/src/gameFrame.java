import java.awt.Color;
import javax.swing.JFrame;

public class gameFrame extends JFrame {

	gameFrame() {
		
		this.add(new gamePanel());
		this.setTitle("Brick Breaker");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(new Color(147, 202, 237));
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
}
