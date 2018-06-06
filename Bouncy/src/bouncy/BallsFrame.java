package bouncy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallsFrame extends JPanel 
{
	private static final long serialVersionUID = 1;
	
	private static int height = 1000;
	private static int width = 1500;
	private static JFrame frame = null;
	private static ArrayList<Ball> balls = new ArrayList<Ball>();
	private static ArrayList<Color> colors = new ArrayList<Color>();
	private static int colorI = 0;
	
	public static BallsFrame initBallsFrame()
	{
		frame = new JFrame("Bouncy");
		BallsFrame b = new BallsFrame();
		frame.add(b);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initColors();
		b.setBgColor();
		
		return b;
	}
	
	public void startBalls() throws InterruptedException
	{
		while (true)
		{
			repaint();
			Thread.sleep(10);
		}
	}
	
	private static void initColors()
	{
		colors.add(Color.RED);
		colors.add(Color.BLUE);
		//colors.add(Color.BLACK);
		colors.add(Color.WHITE);
		colors.add(Color.PINK);
		colors.add(Color.YELLOW);
	}
	
	public static void addBall()
	{
		int size = (int)(200 * Math.random()) + 20;
		int pos = (int)(300 * Math.random());
		int speedHoriz = (int)(10 * Math.random()) + 1;
		int speedVert = (int)(10 * Math.random()) + 1;
		Color color = colors.get(colorI);
		colorI++;
		if (colorI >= colors.size())
		{
			colorI = 0;
		}
		
		Ball ball = Ball.factory(size, size, pos, pos, Ball.MOVEMENT_VERT_DOWN, Ball.MOVEMENT_HORIZ_RIGHT);
		ball.setSpeedVert(speedVert);
		ball.setSpeedHoriz(speedHoriz);
		ball.setColor(color);
		balls.add(ball);
	}
	
	@Override public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		//setBgColor();
		
		PointerInfo pi = MouseInfo.getPointerInfo();
		Point mouseLocation = pi.getLocation();
		int mouseY = (int)mouseLocation.getY();
		int mouseX = (int)mouseLocation.getX();
		
		g2d.setColor(Color.WHITE);
		g2d.drawLine(0, mouseY, width, mouseY);
		g2d.drawLine(mouseX, 0, mouseX, height);
		
		for(int i=0; i<balls.size(); i++)
		{
			Ball ball = balls.get(i);
			ball.drawBall(g2d);
			ball.moveBall(frame);
		}
	}
	
	private void setBgColor()
	{
		//int bgColorI = (int)(5 * Math.random());
		//Color bgColor = colors.get(bgColorI);
		//this.setBackground(bgColor);
		this.setBackground(Color.BLACK);
	}
	
}
