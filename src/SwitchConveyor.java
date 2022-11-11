import java.awt.*;
public class SwitchConveyor extends Conveyor {
	public SwitchConveyor(int x, int y, int length, float slope)
	{
		super(x, y, length, slope);
	}
	public void flip()
	{
		super.setIsMoving(!super.getIsMoving());
	}
	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.setColor(Color.gray);
		g.fillRect(super.getX() + super.getLength()/2*25, super.getY()+10, 30, 20);
		if(super.getIsMoving())
		{
			g.setColor(Color.red.darker());
			g.fillRect(super.getX() + 5 + super.getLength()/2*25, super.getY()+15, 20, 10);
			g.setColor(Color.green);
			g.fillRect(super.getX() + 10 + super.getLength()/2*25, super.getY()+10, 20, 10);
		} else
		{
			g.setColor(Color.red);
			g.fillRect(super.getX() + 5 + super.getLength()/2*25, super.getY()+15, 20, 10);
			g.setColor(Color.green.darker());
			g.fillRect(super.getX() + 10 + super.getLength()/2*25, super.getY()+10, 20, 10);
		}
	}
}
