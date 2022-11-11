import java.awt.*;
import javax.swing.*;
public class Line {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void setX(int x1, int x2)
	{ 
		this.x1 = x1;
		this.x2 = x2;
	}
	public int getX1() { return x1; }
	public int getX2() { return x2; }
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
	}
}
