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
		this.frame = 0;
		this.updown = false;
		this.lines = new ArrayList<Line>();
		for(int i = 0; i < length; i++)
		{
			lines.add(new Line(this.x + 25*i, this.y , (int)(this.x + 25 * i + slope * 100), (int)(this.y - this.slope * 100)));
		}
		this.isMoving = true;
		update();
	}
	
	public boolean getIsMoving() { return this.isMoving; }
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getLength() { return this.length; }
	public void setIsMoving(boolean a) { this.isMoving = a; }
	
	public void update()
	{
		if(this.isMoving)
		{
			int fblx = this.x;
			int fbly = this.y;
			int fbrx = this.x + this.length * 25;
			int fbry = this.y;
			int ftrx = (int)(this.x + this.length * 25 + 100 * this.slope);
			int ftry = (int)(this.y - 100 * this.slope);
			int ftlx = (int)(this.x + 100 * this.slope);
			int ftly = (int)(this.y - 100 * this.slope);
			pointsx = new int[] {fblx, fbrx, ftrx, ftlx};
			pointsy = new int[] {fbly, fbry, ftry, ftly};
			for(Line line: this.lines)
			{
				if(line.getX1() <= this.x + this.length * 25)
				{
					line.setX(line.getX1()+1, line.getX2()+1);
				} else
				{
					line.setX(x, (int)(this.x + 100 * this.slope));
				}
			}
			this.frame++;
			if(this.frame % 20 == 0)
				this.updown = !this.updown;
		}
			
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.GRAY.darker());
		g.fillPolygon(new Polygon(this.pointsx, this.pointsy, 4));
		for(Line line: this.lines)
		{
			line.draw(g);
		}
		g.setColor(Color.gray.darker().darker().darker());
		g.fillRect(this.x, this.y, this.length*25, 10);
		for(int i = 0; i < this.length; i++)
		{
			g.setColor(Color.gray);
			g.fillOval(this.x+i*25, this.y, 10, 10);
			if(this.updown)
			{
				g.setColor(Color.white);
				g.fillRect(this.x+i*25, this.y+5, 2, 2);
				g.fillRect(this.x+i*25+8, this.y+5, 2, 2);
				g.setColor(Color.darkGray.darker());
				g.fillRect(this.x+i*25+4, this.y+1, 2, 2);
				g.fillRect(this.x+i*25+4, this.y+8, 2, 2);
			} else
			{
				g.setColor(Color.darkGray.darker());
				g.fillRect(this.x+i*25, this.y+5, 2, 2);
				g.fillRect(this.x+i*25+8, this.y+5, 2, 2);
				g.setColor(Color.white);
				g.fillRect(this.x+i*25+4, this.y+1, 2, 2);
				g.fillRect(this.x+i*25+4, this.y+8, 2, 2);
			}
		}
	}
}
