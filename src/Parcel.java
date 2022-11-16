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
	private boolean scanned;
	private Color shadeColor;
	private Color lightColor;
	private BoxType type;
	
	public Parcel(int x, int y, float slope)
	{
		this.x = x;
		this.y = y;
		this.slope = slope;
		this.width = (int)(Math.random() * 40 + 10);
		this.height = (int)(Math.random() * 30 + 20);
		this.length = (int)(Math.random() * 30 + 20);
		this.topx = new int[4];
		this.sidex = new int[4];
		this.frontx = new int[4];
		this.topy = new int[4];
		this.sidey = new int[4];
		this.fronty = new int[4];
		double randNum = Math.random();
		if(randNum < 0.33)
			this.type = BoxType.Domestic;
		else if(randNum > 0.33 && randNum < 0.66)
			this.type = BoxType.International;
		else
			this.type = BoxType.Unknown;
		switch(this.type)
		{
			case Domestic:
				this.lightColor = new Color(76/255.f, 252/255.f, 138/255.f);
				this.shadeColor = new Color(60/255.f, 171/255.f, 99/255.f);
				break;
			case International:
				this.lightColor = new Color(77/255.f, 139/255.f, 255/255.f);
				this.shadeColor = new Color(60/255.f, 106/255.f, 171/255.f);
				break;
			case Unknown:
				this.lightColor = new Color(255/255.f, 252/255.f, 59/255.f);
				this.shadeColor = new Color(171/255.f, 169/255.f, 60/255.f);
				break;
			default:
				this.lightColor = Color.gray;
				this.shadeColor = Color.DARK_GRAY;
				break;
		}
		update();
	}
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void scan() { this.scanned = true; }
	public BoxType getType() { return this.type; }
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public boolean getScanned() { return this.scanned; }
	public void update()
	{
		int flbx = this.x;															//	(bltx, blty)    (brtx, brty)
		int flby = this.y;															//      	 +------------+
		int fltx = this.x;															//      	/             /
		int flty = this.y - this.height;											//	       /             /|				
		int frtx = this.x + this.width;												//        /             / |				
		int frty = this.y - this.height;											//  (fltx, flty)  (frtx, frty)
		int frbx = this.x + this.width;												//	    +--------------+  |					
		int frby = this.y;															//	    |              |  |
		int brbx = (int)(this.x + this.width + this.length * this.slope);			//	    |              |  |
		int brby = (int)(this.y - this.length * this.slope);						//	    |              |  |
		int brtx = (int)(this.x + this.width + this.length * this.slope);			//	    |              |  |
		int brty = (int)(this.y - this.height - this.length * this.slope);			//	    |              |  |
		int bltx = (int)(this.x + this.length * this.slope);						//	    |              |  +
		int blty = (int)(this.y - this.height - this.length * this.slope);			//	    |              | / (brbx, brby)
		this.frontx = new int[] {flbx, fltx, frtx, frbx, flbx};						//	    |              |/	
		this.fronty = new int[] {flby, flty, frty, frby, flby};						//	    +______________+		
		this.sidex = new int[] {frtx, frbx, brbx, brtx, frtx};						//	  (flbx, flby)  (frbx, frby)	
		this.sidey = new int[] {frty, frby, brby, brty, frty};
		this.topx = new int[] {fltx, frtx, brtx, bltx, fltx};
		this.topy = new int[] {flty, frty, brty, blty, frty};
	}
	public void draw(Graphics2D g)
	{
		g.setColor(this.lightColor);
		g.fillPolygon(this.frontx, this.fronty, 5);
		g.setColor(this.lightColor);
		g.fillPolygon(this.topx, this.topy, 5);
		g.setColor(this.shadeColor);
		g.fillPolygon(this.sidex, this.sidey, 5);
		g.setColor(Color.black);
		g.drawPolyline(this.topx, this.topy, 5);
		g.drawPolyline(this.frontx, this.fronty, 5);
		g.drawPolyline(this.sidex, this.sidey, 5);
		
	}
}
