import java.awt.*;
public class AutoConveyor extends Conveyor {
	
	public AutoConveyor(int x, int y, int length, float slope)
	{
		super(x, y, length, slope);
		super.setIsMoving(false);
	}
	
	public void checkForParcels(Parcel[] parcels)
	{
		super.setIsMoving(false);
		for(Parcel parcel: parcels)
		{
			if(parcel.getX() > super.getX() && parcel.getX() < super.getX() + super.getLength() * 25 && parcel.getY() < super.getY() && parcel.getY() > super.getY() - 100)
			{
				super.setIsMoving(true);
			}
		}
	}
	
}
