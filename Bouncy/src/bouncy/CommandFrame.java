package bouncy;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CommandFrame extends JFrame
					   implements ActionListener
{
	private static final long serialVersionUID = 1;
	JButton btnAddBall = new JButton("Add Ball");
	
	public CommandFrame() 
	{
		setSize(60, 60);
		getContentPane().add(btnAddBall, BorderLayout.CENTER);
		btnAddBall.setActionCommand("AddBall");
		btnAddBall.addActionListener(this);
	}
	
	public void doWindow()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("AddBall"))
		{
			BallsFrame.addBall();
		}
	}
	
	
	
}
