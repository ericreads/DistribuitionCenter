import java.awt.*;
import javax.swing.*;
public class Parcel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int length;
	private BoxType type;
	
	public Parcel(int x, int y)
	{
		this.x = x;
		this.y = y;
		width = (int)(Math.random() * 40 + 10);
		height = (int)(Math.random() * 30 + 20);
		length = (int)(Math.random() * 30 + 20);
		double randNum = Math.random();
		if(randNum < 0.33)
			type = BoxType.Domestic;
		else if(randNum > 0.33 && randNum < 0.66)
			type = BoxType.International;
		else
			type = BoxType.Unkown;
	}
}
