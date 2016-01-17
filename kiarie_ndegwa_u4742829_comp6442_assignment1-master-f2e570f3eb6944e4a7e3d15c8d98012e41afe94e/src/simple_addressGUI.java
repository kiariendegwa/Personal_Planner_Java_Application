import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Collections;
/**
 * 
 * @author kiarie Ndegwa, u4742829
 * Comp6442: Assignment 1: Addressbook JSwing developement
 * Extra tasks carried out:
 * 1. Help text blurbs over buttons to help with program navigation
 * 2. Avoiding race conditions by enabling single running instance of program
 * 3. Extra attributes to class
 * 4. User friendly interface
 * 5. JUnit testing
 * 6. Address book is ordered alphabetically according to the contacts last name 
 *
 */
public class simple_addressGUI implements Runnable, ActionListener, ListSelectionListener{
	private static final String SAVECOMMAND = "savecommand";
	private static final String DELETECOMMAND = "deletecommand";
	private static final String SEARCHCOMMAND = "searchcommand";
	private static final String ADDCOMMAND = "addcommand";
	private static final String VIEWCOMMAND = "viewcommand";
																																																																																																																																																																																																																																																																																																																									private static final String EDITCOMMAND = "editcommand";
	
	JFrame jframe;
	JDialog contact_window, view_contact;
	JPanel jlistpanel, mainpanel, contact_info, search_panel;
	JTextField LastName, FirstName, Phone, search_text, e_mail, je_mail;
	JTextField dispLastName, dispFirstName, dispPhoneNo, jlastname, jfirstname, jphone_view;
	JList jlist;
	JLabel jlabel, jpic;
	Contacts contact;
	DefaultListModel model;
	Contacts contactData;
	AddressBook addressbook, addressbookData;
	Color lightBLUE = new Color(0, 0, 182, 155);
	
	boolean ContactShow, contact_edit;

	public simple_addressGUI(){
		SwingUtilities.invokeLater(this);
	}
	public static void main(String[] args) {
		
		if(lockInstance("test.txt") == true){
		new simple_addressGUI();
		}
		else if (lockInstance("test.txt") == false){
			System.out.println("Other instance of program already running!");
		}
	}
	
	@Override
	public void run() {
		ContactShow = false;
		model = new DefaultListModel();
		contact = new Contacts();
		jlist = new JList(model);
		jlist.setCellRenderer(new myRenderer());
		
		//Address books
		addressbookData = new AddressBook();
		
		jframe = new JFrame("Simple JSwing address book");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contact_window = new JDialog(jframe, Dialog.ModalityType.APPLICATION_MODAL);
		view_contact = new JDialog(jframe, Dialog.ModalityType.APPLICATION_MODAL);
		
		
		
		mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		mainpanel.setBackground(lightBLUE);
		jlistpanel = new JPanel();
		jlistpanel.setBackground(lightBLUE);
		jlist.addListSelectionListener(this);
		JScrollPane pane = new JScrollPane(jlist);
		LastName = new JTextField(10);
		FirstName = new JTextField(10);
		Phone = new JTextField(10);
		JLabel saved = new JLabel("<html><font color = 'white'>Address Book</font></html>");
		ImageIcon addressImage = new ImageIcon("src/Resources/addressbook.png");
     	Image addressImage1 = addressImage.getImage();
     	Image addressimage = addressImage1.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ; 
     	addressImage= new ImageIcon(addressimage);
     	saved.setIcon(addressImage);
		jlistpanel.add(saved);
		jlistpanel.add(pane);
		//JList containing saved contact	      	
		
		//mainpanel.add(jlist);   
		addressbook =AddressBook.load();
		Collections.sort(addressbook.contacts, new AddressBook());
		//add components from address book to display
		jlist.setListData(addressbook.contacts.toArray());
		jlistpanel.setLayout(new BoxLayout(jlistpanel, BoxLayout.Y_AXIS));
		//Entry point for address book
		JLabel jpic2 = new JLabel();
		ImageIcon conImage2 = new ImageIcon("src/Resources/contact.png");
     	Image conImage11 = conImage2.getImage();
     	Image conimage2 = conImage11.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH ) ; 
     	conImage2= new ImageIcon(conimage2);
     	jpic2.setIcon(conImage2);
		mainpanel.add(jpic2);
		
		
		JLabel LastLabel = new JLabel("Last Name");
		mainpanel.add(LastLabel);
		mainpanel.add(FirstName);
		JLabel FirstLabel = new JLabel("First Name");
		mainpanel.add(FirstLabel);
		mainpanel.add(LastName);
		JLabel PhoneLabel = new JLabel("Phone Number");
		mainpanel.add(PhoneLabel);
		mainpanel.add(Phone);
		e_mail = new JTextField();
		JLabel EmailLabel = new JLabel("E-mail address");
		mainpanel.add(EmailLabel);
		mainpanel.add(e_mail);
		
		//save button
		JButton jbutton = new JButton("Save");
     	jbutton.setActionCommand(SAVECOMMAND);
     	jbutton.addActionListener(this);
     	mainpanel.add(jbutton);
     	
     	//Delete button
     	JButton jbutton1 = new JButton("<html><font color = 'white'>Delete</font></html>");
     	jbutton1.setToolTipText("Click this button to Delete selected contact");
     	jbutton1.setActionCommand(DELETECOMMAND);
     	ImageIcon delImage = new ImageIcon("src/Resources/delete.png");
     	Image delImage1 = delImage.getImage();
     	Image delimage = delImage1.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ; 
     	delImage= new ImageIcon(delimage);
     	jbutton1.addActionListener(this);
     	jbutton1.setIcon(delImage);
     	jbutton1.setBackground(lightBLUE);
     	jlistpanel.add(jbutton1);
     	
     	JButton jbutton40 = new JButton("<html><font color = 'white'>Add Contact</font></html>");
     	jbutton40.setToolTipText("Click this button to add contacts to addressbook.");
     	jbutton40.setActionCommand(ADDCOMMAND);
     	ImageIcon add = new ImageIcon("src/Resources/add.png");
     	Image add1 = add.getImage();
     	Image addimage = add1.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ; 
     	add= new ImageIcon(addimage);
     	jbutton40.addActionListener(this);
     	jbutton40.setBackground(lightBLUE);
     	jlistpanel.add(jbutton40);
     	jbutton40.setIcon(add);
     	
     	
     	JButton jbutton50 = new JButton("Edit Contact");
     	jbutton50.setToolTipText("Click this button save edited information");
     	jbutton50.setActionCommand(EDITCOMMAND);
     	jbutton50.addActionListener(this);
     	
     	JButton jbutton51 = new JButton("<html><font color = 'white'>View Contact</font></html>");
     	jbutton51.setToolTipText("Click this button to view a selected contact, contact can be edited when viewed.");
     	jbutton51.setActionCommand(VIEWCOMMAND);
     	ImageIcon viewImage = new ImageIcon("src/Resources/view.png");
     	Image viewImage1 = viewImage.getImage();
     	Image viewimage = viewImage1.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ; 
     	viewImage= new ImageIcon(viewimage);
     	jbutton51.addActionListener(this);
     	jbutton51.setIcon(viewImage);
     	jbutton51.setBackground(lightBLUE);
     	jlistpanel.add(jbutton51);
     	jlistpanel.add(jbutton1);
     	
     	//Delete button
     	JButton jbutton10 = new JButton("Delete");
     	jbutton10.setToolTipText("Delete contact from address book");
     	jbutton10.setActionCommand(DELETECOMMAND);
     	jbutton10.addActionListener(this);
     	jlistpanel.add(jbutton10);
     	
     	JPanel contact_info = new JPanel();
     	contact_info.setBackground(lightBLUE);
     	contact_info.setLayout(new BoxLayout(contact_info, BoxLayout.Y_AXIS));
     	
     	jlastname = new JTextField();
     	jfirstname = new JTextField();
     	jphone_view = new JTextField();
     	jpic = new JLabel("Contact");
     	ImageIcon conImage = new ImageIcon("src/Resources/contact.png");
     	Image conImage1 = conImage.getImage();
     	Image conimage = conImage1.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH ) ; 
     	conImage= new ImageIcon(conimage);
     	jpic.setIcon(conImage);
     	contact_info.add(jpic);
     	JLabel jLastLabel = new JLabel("Last Name");
     	contact_info.add(jLastLabel);
     	contact_info.add(jlastname);
     	JLabel jFirstLabel = new JLabel("First Name");
     	contact_info.add(jFirstLabel);
     	contact_info.add(jfirstname);
     	JLabel jPhoneLabel = new JLabel("Phone number");
     	contact_info.add(jPhoneLabel);
     	contact_info.add(jphone_view);
     	JLabel E_mailLabel = new JLabel("E-mail");
     	contact_info.add(E_mailLabel);
     	je_mail = new JTextField();
     	contact_info.add(je_mail);
     	contact_info.add(jbutton50);
     	contact_info.add(jbutton10);
     	contact_info.setMinimumSize(new Dimension(200,100));
     	
     	view_contact.getContentPane().add(contact_info, BorderLayout.PAGE_START);
     	view_contact.pack();
     	
     	//save contacts to list
     	addressbook.save();
     	
     	contact_window.getContentPane().add(mainpanel, BorderLayout.PAGE_START);
     	contact_window.pack();
     	
     	//Addition during lab test
     	JDialog number_of_contacts = new JDialog(jframe, Dialog.ModalityType.APPLICATION_MODAL);
     	JLabel number = new JLabel(addressbook.size_great());
     	number_of_contacts.add(number);
     	number_of_contacts.pack();
     	number_of_contacts.setVisible(true);
     	
     	jframe.getContentPane().add(jlistpanel,BorderLayout.CENTER);
		
		jframe.setMinimumSize(new Dimension(350,400));
		jframe.pack();
		
		jframe.setVisible(true);
		view_contact.setMinimumSize(new Dimension(200,150));
    
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		/**This method listens in on action events such as button presses and list selections
		 * 
		 */
		if (ae.getActionCommand().equals(SAVECOMMAND)){
			System.out.println("SAVED");
			Contacts holder = new Contacts(LastName.getText(), FirstName.getText(), Phone.getText(), e_mail.getText());
			//adds data to address book
			addressbook.add(holder);
			Collections.sort(addressbook.contacts, new AddressBook());
			jlist.setListData(addressbook.contacts.toArray());
			ContactShow = false;
			contact_window.setVisible(ContactShow);
			addressbook.save();
			LastName.setText("");
			FirstName.setText("");
			Phone.setText("");
			view_contact.setVisible(false);
			view_contact.dispose();
		}
		else if (ae.getActionCommand().equals(DELETECOMMAND)){
			System.out.println("DELETE CONTACT");
			view_contact.setVisible(false);
			view_contact.dispose();
			
			int[]  fromindex = jlist.getSelectedIndices();
			if (fromindex.length>0){
		
			for(int i =0; i <=fromindex.length-1; i++){
				addressbook.contacts.remove(fromindex[i]);
				jlist.setListData(addressbook.contacts.toArray());
				addressbook.save();
			       }
			}
			
		}
			else if (ae.getActionCommand().equals(ADDCOMMAND)){
				System.out.println("ADD COMMAND");
				ContactShow = true;
				System.out.println(true);
				contact_window.setVisible(ContactShow);
				} 
		 
			else if (ae.getActionCommand().equals(VIEWCOMMAND)){
				System.out.println("VIEW COMMAND");
				Contacts holder = new Contacts(LastName.getText(), FirstName.getText(), Phone.getText(), e_mail.getText());
				
				int[]  fromindex = jlist.getSelectedIndices();
				if (fromindex.length>0){
					Contacts edit =addressbook.contacts.get(fromindex[0]);
					jlastname.setText(edit.getLastName()); 
					jfirstname.setText(edit.getFirstName()); 
					jphone_view.setText(edit.getPhone()); 					
				   }
				view_contact.setVisible(true);
				} 
		
			else if (ae.getActionCommand().equals(EDITCOMMAND)){
				Contacts holder = new Contacts(jlastname.getText(), jfirstname.getText(), jphone_view.getText(), je_mail.getText());
				addressbook.add(holder);
				addressbook.save();
				
				int[]  fromindex = jlist.getSelectedIndices();
				if (fromindex.length>0){
					Contacts edit =addressbook.contacts.get(fromindex[0]);
					addressbook.contacts.remove(fromindex[0]);	
					addressbook.save();
				   }
				jlist.setListData(addressbook.contacts.toArray());
				view_contact.setVisible(false);
				view_contact.dispose();
			}
				
	}
	@Override
	public void valueChanged(ListSelectionEvent ae) {
		addressbook.save();
			}
	
	private static boolean lockInstance(final String lockfile) {
		/**This uses the file lock so as to ensure that only one instance of the program is 
		 * running, preventing race conditions, data loss and file corruption from happening
		 * 
		 */
	    try {
	        final File file = new File(lockfile);
	        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	        final FileLock fileLock = randomAccessFile.getChannel().tryLock();
	        if (fileLock != null) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    try {
	                        fileLock.release();
	                        randomAccessFile.close();
	                        file.delete();
	                    } catch (Exception e) {
	                        
	                    }
	                }
	            });
	            return true;
	        }
	    } catch (Exception e) {
	        System.out.println("Unable to create and/or lock file ContactData.ser"+e);
	    }
	    return false;
	}
	
	
public class myRenderer extends DefaultListCellRenderer
	 {
	/**This class is used to make the jlist more esthetically appealing
	 * This given more time with a map could've been used to display contact images
	 * 
	 */
	     @Override
	     public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) 
	     {
	    	JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			    	ImageIcon contactImage = new ImageIcon("src/Resources/contact.png");
			     	Image contactImage1 = contactImage.getImage();
			     	Image contactimage = contactImage1.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ; 
			     	contactImage= new ImageIcon(contactimage);
	    	        label.setIcon(contactImage);
	    	        label.setHorizontalTextPosition(JLabel.RIGHT);
	    	        
	    	        return label; 
	     }
	 }
}
