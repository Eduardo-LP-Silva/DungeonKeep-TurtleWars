package dkeep.gui;

import javax.swing.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainWindow extends JFrame
{
	private JFrame frame;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Number of Ogres");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
