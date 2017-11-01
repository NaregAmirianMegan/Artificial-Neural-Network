/**
 * Author: Nareg Amirian Megan
 * Title: Graph.java
 * Source Files: Point.java
 * Purpose: Provide a simple graph functionality to graph error
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph {
	
	ArrayList<Point> data = new ArrayList<Point>();
	
	/**
	 * Set up graph with point data
	 * 
	 * @param d
	 * 			array list of points
	 */
	public Graph(ArrayList<Point> d) {
		data = d;
	}
	
	/**
	 * Present graph with built in titles
	 */
	public void show() {
		JFrame frame = new JFrame("Error Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 
		JPanel panel = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(new Color(255, 255, 255));
				g.drawLine(3, 600, 740, 600);
				g.drawLine(3, 3, 3, 600);
				for(int i = 0; i < data.size(); i++) {
					g.setColor(new Color(255, 0, 0));
					g.fillOval((int)data.get(i).getX(), 
							600-(int)data.get(i).getY(), 5, 5);
				}
			}
		};
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		panel.setBackground(new Color(0, 0, 0));
		JLabel label = new JLabel("ITERATION vs. PERCENT ERROR");
		label.setLocation(40, 40);
		label.setSize(100, 100);
		panel.add(label);
		frame.setSize(750, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}