import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Conveyor {
	private int x;
	private int y;
	private int length;
	private int frame;
	private int[] pointsx;
	private int[] pointsy;
	private boolean isMoving;
	private float slope;
	private boolean updown;
	private ArrayList<Line> lines;
	
	public Conveyor(int x, int y, int length, float slope)
	{
		this.x = x;
		this.y = y;
		this.slope = slope;
		this.length = length;
		frame = 0;
		updown = false;
		lines = new ArrayList<Line>();
		for(int i = 0; i < length; i++)
		{
			lines.add(new Line(x + 25*i, y , (int)(x + 25 * i + slope * 100), (int)(y - slope * 100)));
		}
		isMoving = true;
		update();
	}
	
	public boolean getIsMoving() { return isMoving; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getLength() { return length; }
	public void setIsMoving(boolean a) { isMoving = a; }
	
	public void update()
	{
		if(isMoving)
		{
			int fblx = x;
			int fbly = y;
			int fbrx = x + length * 25;
			int fbry = y;
			int ftrx = (int)(x + length * 25 + 100 * slope);
			int ftry = (int)(y - 100 * slope);
			int ftlx = (int)(x + 100 * slope);
			int ftly = (int)(y - 100 * slope);
			pointsx = new int[] {fblx, fbrx, ftrx, ftlx};
			pointsy = new int[] {fbly, fbry, ftry, ftly};
			for(Line line: lines)
			{
				if(line.getX1() <= x + length * 25)
				{
					line.setX(line.getX1()+1, line.getX2()+1);
				} else
				{
					line.setX(x, (int)(x + 100 * slope));
				}
			}
			frame++;
			if(frame % 20 == 0)
				updown = !updown;
		}
			
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.GRAY.darker());
		g.fillPolygon(new Polygon(pointsx, pointsy, 4));
		for(Line line: lines)
		{
			line.draw(g);
		}
		g.setColor(Color.gray.darker().darker().darker());
		g.fillRect(x, y, length*25, 10);
		for(int i = 0; i < length; i++)
		{
			g.setColor(Color.gray);
			g.fillOval(x+i*25, y, 10, 10);
			if(updown)
			{
				g.setColor(Color.white);
				g.fillRect(x+i*25, y+5, 2, 2);
				g.fillRect(x+i*25+8, y+5, 2, 2);
				g.setColor(Color.darkGray.darker());
				g.fillRect(x+i*25+4, y+1, 2, 2);
				g.fillRect(x+i*25+4, y+8, 2, 2);
			} else
			{
				g.setColor(Color.darkGray.darker());
				g.fillRect(x+i*25, y+5, 2, 2);
				g.fillRect(x+i*25+8, y+5, 2, 2);
				g.setColor(Color.white);
				g.fillRect(x+i*25+4, y+1, 2, 2);
				g.fillRect(x+i*25+4, y+8, 2, 2);
			}
		}
	}
}
