////// Imports
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


////// Class definitions

/** class GraphicsProgram
 *
 * Uses Swing library components to:
 * - create a JFrame
 * - create a JPanel
 * - add a JButton and JTextField
 * - set up an Event Handler for the JButton
 * - register the Event Handler as a handler for the JButton events
 * And that is it.  When the events happen, the Event Handler runs.
 */
public class GraphicsProgram
	implements
		ActionListener // an Interface that handles events from many Swing GUI components
{
	int pressCount = 0; // how many times has our button been pressed?
	JTextField jtf;

	/** GraphicsProgram constructor
	 *
	 * Does the actual GUI setup.
	 */
	GraphicsProgram() {
		// Create a JFrame and configure it.
		JFrame jf = new JFrame("Name of Application");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create an empty blank JPanel and give it a size.
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(700, 500));
		jp.setBackground(new Color(240, 200, 40));

		// Create a JButton, register an Event Handler, and add the button to our JPanel.
		JButton jb = new JButton("Press me");
		jb.addActionListener(this); // the Event Handler for button presses
		jb.setActionCommand("PressMe");
		jp.add(jb);

		// Create a JTextField, give it a size, and add it to the JPanel.
		jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(200, 45));
		jp.add(jtf);
		
		// Add the JPanel to the JFrame, make the JFrame visible, and let it run.
		jf.add(jp); // insert JPanel into JFrame
		jf.pack(); // set size of top-level window based on its contents
		jf.setVisible(true);
		
		// Set up and start Timer.
		Timer theTimer = new Timer(500, this);
		theTimer.setActionCommand("Timer");
		theTimer.start();
	}

	/** actionPerformed()
	 *
	 * actionPerformed() is called by the Swing library whenever an event occurs that this object subscribes to.
	 * This object subscribes to other objects' events through other objects' calls to addActionListener().
	 *
	 * actionPerformed is an abstract method of ActionListener.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("PressMe")) {
			System.out.printf("Button pressed %dx, text is: %s\n", ++pressCount, jtf.getText());
		} else if (ae.getActionCommand().equals("Timer")) {
			System.out.print(".");
		}
	}

	static public void main(String[] args) {
		GraphicsProgram sc = new GraphicsProgram();
	}

} // end class GraphicsProgram