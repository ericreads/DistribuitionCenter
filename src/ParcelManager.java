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
		this.parcels = new Parcel[count];
		for(int i = 0; i < this.parcels.length; i++)
		{
			this.parcels[i] = new Parcel(-100, screenHeight / 2, slope);
		}
		this.pixMove = 0;
		this.activeCount = 0;
		this.scanner = new Scanner(615, 500, screenWidth, screenHeight, slope);
		this.paused = false;
		this.switchConveyor = new SwitchConveyor(-100, screenHeight/2+10, 30, slope);
		this.domesticConveyor = new AutoConveyor(650, screenHeight/2+10, 15, slope);
		this.internationalConveyor = new AutoConveyor(650, screenHeight/3+10, 15, slope);
		this.unknownConveyor = new AutoConveyor(650, screenHeight/3*2+10, 15, slope);
	}
	public void update()
	{
		if(this.pixMove % 200 == 0 && this.activeCount < this.parcels.length)
			this.activeCount++;
		for(int i = 0; i < this.activeCount; i++)
		{
			if(this.paused)
			{
				if(this.parcels[i].getScanned())
					this.parcels[i].setX(this.parcels[i].getX() + 1);
			} else
				this.parcels[i].setX(this.parcels[i].getX() + 1);
					
		}
		for(int i = 0; i < this.parcels.length; i++)
		{
			this.parcels[i].update();
		}
		this.domesticConveyor.checkForParcels(parcels);
		this.internationalConveyor.checkForParcels(parcels);
		this.unknownConveyor.checkForParcels(parcels);
		this.scanner.scan(parcels);
		if(!this.paused) this.pixMove++;
		this.switchConveyor.update();
		this.domesticConveyor.update();
		this.internationalConveyor.update();
		this.unknownConveyor.update();
	}
	public void draw(Graphics2D g)
	{
		this.scanner.drawBG(g);
		this.switchConveyor.draw(g);
		this.domesticConveyor.draw(g);
		this.internationalConveyor.draw(g);
		this.unknownConveyor.draw(g);
		for(int i = 0; i < this.parcels.length; i++)
		{
			this.parcels[i].draw(g);
		}
		this.scanner.drawFG(g);
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			this.paused = !this.paused;
			this.switchConveyor.flip();
		}
	}
	
}
