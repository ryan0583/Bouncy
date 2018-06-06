package bouncy;

public class Bouncy 
{
	public static void main(String args[]) throws InterruptedException
	{
		BallsFrame bf = BallsFrame.initBallsFrame();
		CommandFrame cf = new CommandFrame();
		cf.doWindow();
		bf.startBalls();
	}
}
