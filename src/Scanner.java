import java.awt.*;
import javax.swing.*;

public class Scanner {
	private int x;
	private int y;
	private boolean lightOn;
	private static int WIDTH = 100;
	private static int HEIGHT = 400;
	private int screenWidth;
	private int screenHeight;
	private Screen screen;
	
	public Scanner(int x, int y, int screenWidth, int screenHeight)
	{
		this.x = x;
		this.y = y;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		lightOn = false;
		screen = new Screen(50, 400);
	}
	public void scan(Parcel[] parcels)
	{
		lightOn = false;
		for(Parcel parcel : parcels)
		{
			if(parcel.getX() > x && parcel.getX() < x + WIDTH && parcel.getY() > y && parcel.getY() < y + HEIGHT)
			{
				lightOn = true;
				screen.setType(parcel.getType());
				parcel.scan();
				switch(parcel.getType())
				{
					case International:
						parcel.setY(screenHeight / 3);
						break;
					case Domestic:
						break;
					case Unknown:
						parcel.setY((screenHeight / 3) * 2);
						break;
					default:
						break;
				}
			}
		}
	}
	public void draw(Graphics2D g)
	{
		g.setColor(Color.gray);
		g.fillRect(x, y, WIDTH, HEIGHT);
		if(lightOn)
			g.setColor(Color.red);
		else
			g.setColor(Color.darkGray);
		g.fillRect(x+20, y+10, 10, 10);
		screen.draw(g);
	}
}
