import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/*** This simple program is a simple contact address book.
 -it will be user friendly and intuitive
 -android enabled
 -have a search option
 -save contact details in a Hashmap
 -have a simple to do list
 **/
public class AddressBook_GUI implements Runnable, ActionListener{
	private static final String EXITCOMMAND = "exitcommand";
	private static final String ADDCONTACT = "addcontact";
	private static final String DELETECONTACT = "deletecontact";
	private static final String ADDTASK = "addtask";
	private static final String TASK = "task";
	private static final String DELETETASK = "deletetask";
	private static final String ADDRESS = "address";
	private static final String SEARCH = "search";
	
	JFrame jframe;
	JList jlist;
	JButton Address_book;
	JButton To_do_list;
	
	public AddressBook_GUI() {
		 SwingUtilities.invokeLater(this);
	}
	public static void main(String[] args) {
		new AddressBook_GUI();
	}
	
	public void run(){
		jframe = new JFrame("Address");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().add(new JLabel("Address"));
		//
		JPanel Address = new JPanel();
		Address.setLayout(new BoxLayout(Address,BoxLayout.Y_AXIS));
		JPanel To_do = new JPanel();
		To_do.setLayout(new BoxLayout(To_do,BoxLayout.Y_AXIS));
		JPanel Search = new JPanel();
		Search.setLayout(new BoxLayout(Search,BoxLayout.Y_AXIS));
		
	    //Menu bar for address book 
		JMenuBar bar = new JMenuBar();
		JMenu menu  = new JMenu("File");
     	JMenu menu2 = new JMenu("Help");
     	JMenu menu3 = new JMenu("Contacts");
     	JMenu menu4 = new JMenu("To do list");
     	JMenu menu5 = new JMenu("About");
     	JMenu menu6 = new JMenu("Contacts");
     	
     	bar.add(menu);
     	bar.add(menu3);
     	bar.add(menu4);
     	bar.add(menu2);
     	bar.add(menu5);
     	
 /******Contacts tab******/
     	JMenuItem menuitem = new JMenuItem("Add Contact");
     	menu3.add(menuitem);
     	menuitem.addActionListener(this);
     	menuitem.setActionCommand(ADDCONTACT);
     	/*Sub directories*/
     	JMenuItem menuitem3 = new JMenuItem("View Address Book");
     	menu3.add(menuitem3);
     	JMenu menuitem5 = new JMenu("Export Address Book");
     	JMenuItem menu22 = new JMenuItem("Export .txt file");
     	JMenuItem menu23 = new JMenuItem("Export .csv file");
     	menuitem5.add(menu22);
     	menuitem5.add(menu23);
     	menu3.add(menuitem5);

     	//To do list tab
     	JMenuItem menuitem2 = new JMenuItem("Add To Do Item");
     	menu4.add(menuitem2);
     	JMenuItem menuitem4 = new JMenuItem("View To do List");
     	menu4.add(menuitem4);
     	
     	JMenu menuitem6 = new JMenu("Export To do List");
     	JMenuItem menu222 = new JMenuItem("Export .txt file");
     	JMenuItem menu233 = new JMenuItem("Export .csv file");
     	menuitem6.add(menu222);
     	menuitem6.add(menu233);
     	menu4.add(menuitem6);
     	
     	//File sub directory
     	JMenu import_add = new JMenu("Import contacts");
     	JMenu import_todo = new JMenu();
     	JMenuItem import_format1 = new JMenuItem("Import .txt file");
     	JMenuItem import_format2 = new JMenuItem("Import .csv file");
     	import_add.add(import_format1);
     	import_add.add(import_format2);
      	menu.add(import_add);
      	
      	//To do sub directors
      	JMenuItem import_format11 = new JMenuItem("Import .txt file");
     	JMenuItem import_format22 = new JMenuItem("Import .csv file");
     	JMenu menuitem9 = new JMenu("Import To do list");
     	menuitem9.add(import_format11);
     	menuitem9.add(import_format22);
     	menu.add(menuitem9);
     	
     	JMenuItem menuitem7 = new JMenuItem("Exit");
     	menu.add(menuitem7);
     	menuitem7.addActionListener(this);
     	menuitem7.setActionCommand(EXITCOMMAND);
     	
     	
     	//Help Tab
     	JMenuItem menuitem10 = new JMenuItem("Address Book");
     	menu2.add(menuitem10);
     	JMenuItem menuitem11 = new JMenuItem("To do list");
     	menu2.add(menuitem11);
  
     	JButton address = new JButton("Address Book");
     	address.setActionCommand(ADDRESS);
     	address.addActionListener(this);
     	Address.add(address);
     	
     	JButton to_do = new JButton("To do list");
     	to_do.setActionCommand(TASK);
     	to_do.addActionListener(this);
     	to_do.add(address);
     	
     	JButton search = new JButton("Search Contacts");
     	search.setActionCommand(SEARCH);
     	search.addActionListener(this);
     	Search.add(search);
     	
     	jframe.getContentPane().add(address,BorderLayout.LINE_START);
     	jframe.getContentPane().add(to_do,BorderLayout.CENTER);
     	jframe.getContentPane().add(search,BorderLayout.LINE_END);
     	
     	jframe.setJMenuBar(bar);
     	jframe.setMinimumSize(new Dimension(300,300));
		jframe.setVisible(true);
     	jframe.pack();
	}
	public void actionPerformed(ActionEvent ae) {
	  if (ae.getActionCommand().equals(EXITCOMMAND)) {
			System.exit(0);
		}
	  else if( ae.getActionCommand().equals(ADDRESS)){
		  System.out.print("Address");
	  }
	  else if( ae.getActionCommand().equals(SEARCH)){
		  System.out.print("Search");
	  }
	  else if( ae.getActionCommand().equals(TASK)){
		  System.out.print("Task");
	  }
	}
}
