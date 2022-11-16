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
		int flbx = this.x;											//	(bltx, blty)    (brtx, brty)
		int flby = this.y;											//      	 +------------+
		int fltx = this.x;											//      	/             /
		int flty = this.y - this.HEIGHT;									//	       /             /|				
		int frtx = this.x + this.WIDTH;									//        /             / |				
		int frty = this.y - this.HEIGHT;									//  (fltx, flty)  (frtx, frty)
		int frbx = this.x + this.WIDTH;									//	    +--------------+  |					
		int frby = this.y;											//	    |              |  |
		int brbx = (int)(this.x + this.WIDTH + this.DEPTH * slope);			//	    |              |  |
		int brby = (int)(this.y - this.DEPTH * slope);					//	    |              |  |
		int brtx = (int)(this.x + this.WIDTH + this.DEPTH * slope);			//	    |              |  |
		int brty = (int)(this.y - this.HEIGHT - this.DEPTH * slope);			//	    |              |  |
		int bltx = (int)(this.x + this.DEPTH * slope);					//	    |              |  +
		int blty = (int)(this.y - this.HEIGHT - this.DEPTH * slope);			//	    |              | / (brbx, brby)
		this.frontx = new int[] {flbx, fltx, frtx, frbx, flbx};		//	    |              |/	
		this.fronty = new int[] {flby, flty, frty, frby, flby};		//	    +______________+		
		this.sidex = new int[] {frtx, frbx, brbx, brtx, frtx};		//	  (flbx, flby)  (frbx, frby)	
		this.sidey = new int[] {frty, frby, brby, brty, frty};
		this.topx = new int[] {fltx, frtx, brtx, bltx, fltx};
		this.topy = new int[] {flty, frty, brty, blty, frty};
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.lightOn = false;
		this.screen = new Screen(50, 400);
	}
	public void scan(Parcel[] parcels)
	{
		this.lightOn = false;
		for(Parcel parcel : parcels)
		{
			if(parcel.getX() > x && parcel.getX() < x + WIDTH && parcel.getY() < y && parcel.getY() > y - HEIGHT)
			{
				this.lightOn = true;
				this.screen.setType(parcel.getType());
				parcel.scan();
				switch(parcel.getType())
				{
					case International:
						parcel.setY(this.screenHeight / 3);
						break;
					case Domestic:
						break;
					case Unknown:
						parcel.setY((this.screenHeight / 3) * 2);
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
		g.fillPolygon(this.sidex, this.sidey, 4);
		g.setColor(Color.gray);
		g.fillPolygon(this.topx, this.topy, 4);
	}
	public void drawFG(Graphics2D g)
	{
		g.setColor(Color.gray);
		g.fillPolygon(new Polygon(this.frontx, this.fronty, 4));
		if(this.lightOn)
			g.setColor(Color.red);
		else
			g.setColor(Color.darkGray);
		g.fillRect(this.x+20, this.y-this.HEIGHT+10, 10, 10);
		g.setColor(Color.black);
		//g.drawPolyLine(topx, topy, 5);
		
		this.screen.draw(g);
	}
}
