import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Screen {
	private int x;
	private int y;
	private static int WIDTH = 200;
	private static int HEIGHT = 200;
	private BoxType currentBox;
	private Image truck;
	private Image plane;
	private Image questionMark;
	
	public Screen(int x, int y)
	{
		this.x = x;
		this.y = y;
		try {
			this.truck = ImageIO.read(new File("truck.png")).getScaledInstance(this.WIDTH, this.HEIGHT, Image.SCALE_DEFAULT);
			this.plane = ImageIO.read(new File("plane.png")).getScaledInstance(this.WIDTH, this.HEIGHT, Image.SCALE_DEFAULT);
			this.questionMark = ImageIO.read(new File("questionMark.png")).getScaledInstance(this.WIDTH, this.HEIGHT, Image.SCALE_DEFAULT);
		} catch (IOException e) {}
	}
	
	public void setType(BoxType type)
	{
		this.currentBox = type;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
		if(this.currentBox != null)
		{
			switch(this.currentBox)
			{
				case International:
					g.drawImage(this.plane, this.x, this.y, null);
					break;
				case Domestic:
					g.drawImage(this.truck, this.x, this.y, null);
					break;
				case Unknown:
					g.drawImage(this.questionMark, this.x, this.y, null);
					break;
				default:
					break;
			}
		}else
		{
			//Eliminate lag of drawing image for the first time
			g.drawImage(this.plane, this.x, this.y, null);
			g.drawImage(this.truck, this.x, this.y, null);
			g.drawImage(this.questionMark, this.x, this.y, null);
			g.fillRect(this.x, this.y, this.WIDTH, this.HEIGHT);
		}
		
	}
}
