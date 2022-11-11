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
			truck = ImageIO.read(new File("truck.png")).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
			plane = ImageIO.read(new File("plane.png")).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
			questionMark = ImageIO.read(new File("questionMark.png")).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
		} catch (IOException e) {}
	}
	
	public void setType(BoxType type)
	{
		currentBox = type;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
		if(currentBox != null)
		{
			switch(currentBox)
			{
				case International:
					g.drawImage(plane, x, y, null);
					break;
				case Domestic:
					g.drawImage(truck, x, y, null);
					break;
				case Unknown:
					g.drawImage(questionMark, x, y, null);
					break;
				default:
					break;
			}
		}else
		{
			//Eliminate lag of drawing image for the first time
			g.drawImage(plane, x, y, null);
			g.drawImage(truck, x, y, null);
			g.drawImage(questionMark, x, y, null);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
		
	}
}
