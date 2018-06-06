package bouncy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.JFrame;

public class Ball
{
	public static final int MOVEMENT_VERT_NONE = 0;
	public static final int MOVEMENT_VERT_DOWN = 1;
	public static final int MOVEMENT_VERT_UP = 2;
	
	public static final int MOVEMENT_HORIZ_NONE = 0;
	public static final int MOVEMENT_HORIZ_RIGHT = 1;
	public static final int MOVEMENT_HORIZ_LEFT = 2;
	
	
	private int ovalWidth = -1;
	private int ovalHeight = -1;
	private int verticalPos = -1;
	private int horizontalPos = -1;
	private int movementVert = -1;
	private int movementHoriz = -1;
	private int minOvalHeight = -1;
	private int minOvalWidth = -1;
	private int initialOvalHeight = -1;
	private int initialOvalWidth = -1;
	private boolean squishingHoriz = false;
	private boolean squishingVert = false;
	private Color color = Color.WHITE;
	private int speedVert = 1;
	private int speedHoriz = 1;
	
	public static Ball factory(int ovalWidth, int ovalHeight, int verticalPos, int horizontalPos, int movementVert, int movementHoriz)
	{
		Ball retval = new Ball();
		retval.setOvalWidth(ovalWidth);
		retval.setOvalHeight(ovalHeight);
		retval.setVerticalPos(verticalPos);
		retval.setHorizontalPos(horizontalPos);
		retval.setMovementVert(movementVert);
		retval.setMovementHoriz(movementHoriz);
		//retval.setMinOvalHeight((int)(ovalHeight / 1.5));
		//retval.setMinOvalWidth((int)(ovalWidth / 1.5));
		retval.setMinOvalHeight(ovalHeight);
		retval.setMinOvalWidth(ovalWidth);
		retval.setInitialOvalHeight(ovalHeight);
		retval.setInitialOvalWidth(ovalWidth);
		
		return retval;
	}
	
	public void drawBall(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		if (color.equals(Color.BLACK))
		{
			g2d.setColor(Color.BLUE);
		}
		g2d.drawOval(horizontalPos, verticalPos, ovalWidth, ovalHeight);
		
		g2d.setColor(color);
		g2d.fillOval(horizontalPos, verticalPos, ovalWidth, ovalHeight);
	}
	
	public void moveBall(JFrame frame)
	{
		PointerInfo pi = MouseInfo.getPointerInfo();
		Point mouseLocation = pi.getLocation();
		int mouseY = (int)mouseLocation.getY();
		int mouseX = (int)mouseLocation.getX();
		
		moveVert();
		
		moveHoriz();
		
		if (verticalPos < mouseY
		  || verticalPos < 0)
		{
			if (movingUp()
			  && ovalHeight > minOvalHeight)
			{
				squishingVert = true;
			}
			else
			{
				squishingVert = false;
				setMovementVert(MOVEMENT_VERT_DOWN);
			}
		}
		else if (verticalPos + ovalHeight > frame.getHeight() - 30)
		{
			if (movingDown()
			  && ovalHeight > minOvalHeight)
			{
				squishingVert = true;
			}
			else
			{
				squishingVert = false;
				setMovementVert(MOVEMENT_VERT_UP);
			}
		}
		
		if (horizontalPos < mouseX
		  || horizontalPos < 0)
		{
			if (movingLeft()
			  && ovalWidth > minOvalWidth)
			{
				squishingHoriz = true;
			}
			else
			{
				squishingHoriz = false;
				setMovementHoriz(MOVEMENT_HORIZ_RIGHT);
			}
		}
		else if (horizontalPos + ovalWidth > frame.getWidth() - 10)
		{
			if (movingRight()
			  && ovalWidth > minOvalWidth)
			{
				squishingHoriz = true;
			}
			else
			{
				squishingHoriz = false;
				setMovementHoriz(MOVEMENT_HORIZ_LEFT);
			}
		}
	}
	
	public void moveVert()
	{	
		if (movingUp())
		{
			if (squishingVert)
			{
				ovalHeight -= speedVert;
				verticalPos -= speedVert;
			}
			else if (ovalHeight < initialOvalHeight)
			{
				ovalHeight += speedVert;
				verticalPos -= speedVert;
			}
			else
			{
				verticalPos -= speedVert;
			}
		}
		else if (movingDown())
		{
			if (squishingVert)
			{
				ovalHeight -= speedVert;
				verticalPos += speedVert;
			}
			else if (ovalHeight < initialOvalHeight)
			{
				ovalHeight += speedVert;
				verticalPos += speedVert;
			}
			else
			{
				verticalPos += speedVert;
			}
		}
	}
	
	public void moveHoriz()
	{	
		if (movingRight())
		{
			if (squishingHoriz)
			{
				ovalWidth -= speedHoriz;
				horizontalPos += speedHoriz;
			}
			else if (ovalWidth < initialOvalWidth)
			{
				ovalWidth += speedHoriz;
				horizontalPos += speedHoriz;
			}
			else
			{
				horizontalPos += speedHoriz;
			}
		}
		else if (movingLeft())
		{
			if (squishingHoriz)
			{
				ovalWidth -= speedHoriz;
				horizontalPos -= speedHoriz;
			}
			else if (ovalWidth < initialOvalWidth)
			{
				ovalWidth += speedHoriz;
				horizontalPos -= speedHoriz;
			}
			else
			{
				horizontalPos -= speedHoriz;
			}
		}
	}

	private boolean movingUp()
	{
		return movementVert == MOVEMENT_VERT_UP;
	}
	private boolean movingDown()
	{
		return movementVert == MOVEMENT_VERT_DOWN;
	}
	private boolean movingRight()
	{
		return movementHoriz == MOVEMENT_HORIZ_RIGHT;
	}
	private boolean movingLeft()
	{
		return movementHoriz == MOVEMENT_HORIZ_LEFT;
	}
	
	public int getOvalWidth()
	{
		return ovalWidth;
	}
	public void setOvalWidth(int ovalWidth)
	{
		this.ovalWidth = ovalWidth;
	}
	public int getOvalHeight()
	{
		return ovalHeight;
	}
	public void setOvalHeight(int ovalHeight)
	{
		this.ovalHeight = ovalHeight;
	}
	public int getVerticalPos()
	{
		return verticalPos;
	}
	public void setVerticalPos(int verticalPos)
	{
		this.verticalPos = verticalPos;
	}
	public int getHorizontalPos()
	{
		return horizontalPos;
	}
	public void setHorizontalPos(int horizontalPos)
	{
		this.horizontalPos = horizontalPos;
	}
	public int getMovementVert()
	{
		return movementVert;
	}
	public void setMovementVert(int movementVert)
	{
		this.movementVert = movementVert;
	}
	public int getMovementHoriz()
	{
		return movementHoriz;
	}
	public void setMovementHoriz(int movementHoriz)
	{
		this.movementHoriz = movementHoriz;
	}
	public int getMinOvalHeight()
	{
		return minOvalHeight;
	}
	public void setMinOvalHeight(int minOvalHeight)
	{
		this.minOvalHeight = minOvalHeight;
	}
	public int getMinOvalWidth()
	{
		return minOvalWidth;
	}
	public void setMinOvalWidth(int minOvalWidth)
	{
		this.minOvalWidth = minOvalWidth;
	}
	public int getInitialOvalHeight()
	{
		return initialOvalHeight;
	}
	public void setInitialOvalHeight(int initialOvalHeight)
	{
		this.initialOvalHeight = initialOvalHeight;
	}
	public int getInitialOvalWidth()
	{
		return initialOvalWidth;
	}
	public void setInitialOvalWidth(int initialOvalWidth)
	{
		this.initialOvalWidth = initialOvalWidth;
	}
	public boolean getSquishingHoriz()
	{
		return squishingHoriz;
	}
	public void setSquishingHoriz(boolean squishingHoriz)
	{
		this.squishingHoriz = squishingHoriz;
	}
	public boolean getSquishingVert()
	{
		return squishingVert;
	}
	public void setSquishingVert(boolean squishingVert)
	{
		this.squishingVert = squishingVert;
	}
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getSpeedVert()
	{
		return speedVert;
	}
	public void setSpeedVert(int speedVert)
	{
		this.speedVert = speedVert;
	}
	public int getSpeedHoriz()
	{
		return speedHoriz;
	}
	public void setSpeedHoriz(int speedHoriz)
	{
		this.speedHoriz = speedHoriz;
	}
}
