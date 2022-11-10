import java.awt.*;
import javax.swing.*;
public class ParcelManager {
	private Parcel[] parcels;
	private int pixMove;
	private int activeCount;
	public ParcelManager(int count, int screenWidth, int screenHeight, float slope)
	{
		parcels = new Parcel[count];
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i] = new Parcel(-100, screenHeight / 2, slope);
		}
		pixMove = 0;
		activeCount = 0;
	}
	public void update()
	{
		if(pixMove % 200 == 0 && activeCount + 1 < 20)
			activeCount++;
		for(int i = 0; i < activeCount; i++)
		{
			parcels[i].setX(parcels[i].getX() + 1);
		}
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i].update();
		}
		pixMove++;
	}
	public void draw(Graphics2D g)
	{
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i].draw(g);
		}
	}
}
