import java.awt.*;
import javax.swing.*;

public class Scanner {
	private int x;
	private int y;
	private boolean lightOn;
	private static int WIDTH = 100;
	private static int HEIGHT = 400;
	private static int DEPTH = 100;
	private int[] frontx;
	private int[] fronty;
	private int[] sidex;
	private int[] sidey;
	private int[] topy;
	private int[] topx;
	private int screenWidth;
	private int screenHeight;
	private Screen screen;
	
	public Scanner(int x, int y, int screenWidth, int screenHeight, float slope)
	{
		this.x = x;
		this.y = y;
		int flbx = x;											//	(bltx, blty)    (brtx, brty)
		int flby = y;											//      	 +------------+
		int fltx = x;											//      	/             /
		int flty = y - HEIGHT;									//	       /             /|				
		int frtx = x + WIDTH;									//        /             / |				
		int frty = y - HEIGHT;									//  (fltx, flty)  (frtx, frty)
		int frbx = x + WIDTH;									//	    +--------------+  |					
		int frby = y;											//	    |              |  |
		int brbx = (int)(x + WIDTH + DEPTH * slope);			//	    |              |  |
		int brby = (int)(y - DEPTH * slope);					//	    |              |  |
		int brtx = (int)(x + WIDTH + DEPTH * slope);			//	    |              |  |
		int brty = (int)(y - HEIGHT - DEPTH * slope);			//	    |              |  |
		int bltx = (int)(x + DEPTH * slope);					//	    |              |  +
		int blty = (int)(y - HEIGHT - DEPTH * slope);			//	    |              | / (brbx, brby)
		frontx = new int[] {flbx, fltx, frtx, frbx, flbx};		//	    |              |/	
		fronty = new int[] {flby, flty, frty, frby, flby};		//	    +______________+		
		sidex = new int[] {frtx, frbx, brbx, brtx, frtx};		//	  (flbx, flby)  (frbx, frby)	
		sidey = new int[] {frty, frby, brby, brty, frty};
		topx = new int[] {fltx, frtx, brtx, bltx, fltx};
		topy = new int[] {flty, frty, brty, blty, frty};
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
			if(parcel.getX() > x && parcel.getX() < x + WIDTH && parcel.getY() < y && parcel.getY() > y - HEIGHT)
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
	public void drawBG(Graphics2D g)
	{
		g.setColor(Color.gray.darker().darker().darker().darker());
		g.fillPolygon(sidex, sidey, 4);
		g.setColor(Color.gray);
		g.fillPolygon(topx, topy, 4);
	}
	public void drawFG(Graphics2D g)
	{
		g.setColor(Color.gray);
		g.fillPolygon(new Polygon(frontx, fronty, 4));
		if(lightOn)
			g.setColor(Color.red);
		else
			g.setColor(Color.darkGray);
		g.fillRect(x+20, y-HEIGHT+10, 10, 10);
		g.setColor(Color.black);
		//g.drawPolyLine(topx, topy, 5);
		
		screen.draw(g);
	}
}
