import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main_GUI implements Runnable, ActionListener{
AddressBook_GUI Address_window;
public static final String ADDRESS = "address";
public static final String EXITCOMMAND = "exit";
JFrame jframe;
JButton address;
boolean flag = true;

	public Main_GUI() {
		 SwingUtilities.invokeLater(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		jframe = new JFrame("Address");
		address.setActionCommand(ADDRESS);
     	address.addActionListener(this);
		jframe.getContentPane().add(new JLabel("Address"));
     	jframe.setMinimumSize(new Dimension(300,300));
		jframe.setVisible(flag);
     	jframe.pack();	
	}

	
	public static void main(String[] args) {
		new Main_GUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(ADDRESS)) {
			System.out.print("Address");
			flag = !flag;
		} else if (ae.getActionCommand().equals(EXITCOMMAND)) {
			System.exit(0);
		}	
	}
}
