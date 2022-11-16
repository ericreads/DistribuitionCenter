import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
enum BoxType{
	International,
	Unknown,
	Domestic
}
public class DistroJPanel extends JPanel {
	
	private static int WIDTH = 1020;
	private static int HEIGHT = 640;
	private static float SLOPE = 0.5f;
	private static ParcelManager parcels = new ParcelManager(20, WIDTH, HEIGHT, SLOPE);
	
	public DistroJPanel()
	{
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				parcels.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	public void update()
	{
		this.parcels.update();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		this.parcels.draw(g2d);
	}
	
	public static void main(String[] args) throws InterruptedException{
	{
		JFrame frame = new JFrame("Distribution Center");
		DistroJPanel p = new DistroJPanel();
		frame.add(p);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true)
		{
			p.update();
			p.repaint();
			Thread.sleep(10);
		}
	}
	}
}
