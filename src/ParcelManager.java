import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ParcelManager {
	private Parcel[] parcels;
	private int pixMove;
	private int activeCount;
	private Scanner scanner;
	private boolean paused;
	private SwitchConveyor switchConveyor;
	private AutoConveyor domesticConveyor;
	private AutoConveyor internationalConveyor;
	private AutoConveyor unknownConveyor;
	public ParcelManager(int count, int screenWidth, int screenHeight, float slope)
	{
		parcels = new Parcel[count];
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i] = new Parcel(-100, screenHeight / 2, slope);
		}
		pixMove = 0;
		activeCount = 0;
		scanner = new Scanner(615, 120, screenWidth, screenHeight);
		paused = false;
		switchConveyor = new SwitchConveyor(-100, screenHeight/2+10, 30, slope);
		domesticConveyor = new AutoConveyor(650, screenHeight/2+10, 25, slope);
		internationalConveyor = new AutoConveyor(650, screenHeight/3+10, 25, slope);
		unknownConveyor = new AutoConveyor(650, screenHeight/3*2+10, 25, slope);
	}
	public void update()
	{
		if(pixMove % 200 == 0 && activeCount + 1 < parcels.length)
			activeCount++;
		for(int i = 0; i < activeCount; i++)
		{
			if(paused)
			{
				if(parcels[i].getScanned())
					parcels[i].setX(parcels[i].getX() + 1);
			} else
				parcels[i].setX(parcels[i].getX() + 1);
					
		}
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i].update();
		}
		domesticConveyor.checkForParcels(parcels);
		internationalConveyor.checkForParcels(parcels);
		unknownConveyor.checkForParcels(parcels);
		scanner.scan(parcels);
		if(!paused) pixMove++;
		switchConveyor.update();
		domesticConveyor.update();
		internationalConveyor.update();
		unknownConveyor.update();
	}
	public void draw(Graphics2D g)
	{
		switchConveyor.draw(g);
		domesticConveyor.draw(g);
		internationalConveyor.draw(g);
		unknownConveyor.draw(g);
		for(int i = 0; i < parcels.length; i++)
		{
			parcels[i].draw(g);
		}
		scanner.draw(g);
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			paused = !paused;
			switchConveyor.flip();
		}
	}
	
}
