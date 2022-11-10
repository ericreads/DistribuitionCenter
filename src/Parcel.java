import java.awt.*;
import javax.swing.*;
public class Parcel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int length;
	private float slope;
	private int[] topx;
	private int[] sidex;
	private int[] frontx;
	private int[] topy;
	private int[] sidey;
	private int[] fronty;
	private Color shadeColor;
	private Color lightColor;
	private BoxType type;
	
	public Parcel(int x, int y, float slope)
	{
		this.x = x;
		this.y = y;
		this.slope = slope;
		width = (int)(Math.random() * 40 + 10);
		height = (int)(Math.random() * 30 + 20);
		length = (int)(Math.random() * 30 + 20);
		topx = new int[4];
		sidex = new int[4];
		frontx = new int[4];
		topy = new int[4];
		sidey = new int[4];
		fronty = new int[4];
		double randNum = Math.random();
		if(randNum < 0.33)
			type = BoxType.Domestic;
		else if(randNum > 0.33 && randNum < 0.66)
			type = BoxType.International;
		else
			type = BoxType.Unkown;
		switch(type)
		{
			case Domestic:
				lightColor = new Color(76/255.f, 252/255.f, 138/255.f);
				shadeColor = new Color(60/255.f, 171/255.f, 99/255.f);
				break;
			case International:
				lightColor = new Color(77/255.f, 139/255.f, 255/255.f);
				shadeColor = new Color(60/255.f, 106/255.f, 171/255.f);
				break;
			case Unkown:
				lightColor = new Color(255/255.f, 252/255.f, 59/255.f);
				shadeColor = new Color(171/255.f, 169/255.f, 60/255.f);
				break;
			default:
				lightColor = Color.gray;
				shadeColor = Color.DARK_GRAY;
				break;
		}
	}
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void update()
	{
		int flbx = x;
		int flby = y;
		int fltx = x;
		int flty = y - height;
		int frtx = x + width;
		int frty = y - height;
		int frbx = x + width;
		int frby = y;
		int brbx = (int)(x + width + length * slope);
		int brby = (int)(y - length * slope);
		int brtx = (int)(x + width + length * slope);
		int brty = (int)(y - height - length * slope);
		int bltx = (int)(x + length * slope);
		int blty = (int)(y - height - length * slope);
		frontx = new int[] {flbx, fltx, frtx, frbx, flbx};
		fronty = new int[] {flby, flty, frty, frby, flby};
		sidex = new int[] {frtx, frbx, brbx, brtx, frtx};
		sidey = new int[] {frty, frby, brby, brty, frty};
		topx = new int[] {fltx, frtx, brtx, bltx, fltx};
		topy = new int[] {flty, frty, brty, blty, frty};
	}
	public void draw(Graphics2D g)
	{
		g.setColor(lightColor);
		g.fillPolygon(frontx, fronty, 5);
		g.setColor(lightColor);
		g.fillPolygon(topx, topy, 5);
		g.setColor(shadeColor);
		g.fillPolygon(sidex, sidey, 5);
		g.setColor(Color.black);
		g.drawPolyline(topx, topy, 5);
		g.drawPolyline(frontx, fronty, 5);
		g.drawPolyline(sidex, sidey, 5);
		
	}
}
