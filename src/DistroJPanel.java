import java.awt.*;
import javax.swing.*;
enum BoxType{
	International,
	Unkown,
	Domestic
}
public class DistroJPanel extends JPanel {
	
	private static int WIDTH = 1280;
	private static int HEIGHT = 720;
	private static float SLOPE = 0.5f;
	private Parcel parcel = new Parcel(-50, HEIGHT/2, SLOPE);
	
	public void update()
	{
		parcel.update();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		parcel.draw(g2d);
	}
	
	public static void main(String[] args) throws InterruptedException{
	{
		JFrame frame = new JFrame("Cityscape");
		DistroJPanel p = new DistroJPanel();
		frame.add(p);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		while(true)
		{
			p.update();
			p.repaint();
			Thread.sleep(10);
		}
	}
	}
}
