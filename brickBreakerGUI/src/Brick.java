import java.awt.*;

public class brick extends Rectangle {

	boolean isVisible;
	
	public brick(int brickX, int brickY, int width, int height) {
		
		super(brickX, brickY, width, height);
		this.isVisible = true;
		
	}

	public void draw(Graphics g) {
		
		if (isVisible) {
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, width, height);
		}
		
	}
	
	public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
    	
        isVisible = visible;
        
    }

}
