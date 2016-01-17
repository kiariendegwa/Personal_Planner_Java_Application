import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * 
 * @author kiarie Ndegwa u4742829
 * This class generates the array list where all the contacts are stored
 * It arranges them alphabetically by implementing the comparator interface
 * 
 * It stores the generated addressbook as a java serializable object
 * 
 * It is also used by the addressbook GUI to load information to the Display
 * 
 *
 */
class AddressBook implements Serializable, Comparator<Contacts>{
	ArrayList<Contacts> contacts = new ArrayList<Contacts>();
	
	
	public AddressBook(){
		contacts = new ArrayList<Contacts>();
	}
	
	
	public void add(Contacts contact){
		contacts.add(contact);
	}
	
	public 	ArrayList<Contacts> get(){
		return this.contacts;
	}
	
	public void remove(Contacts delcontact){
		
		contacts.remove(contacts.indexOf(delcontact));
	}
	
	static public AddressBook load() {
		AddressBook res = new AddressBook();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"ContactData.ser"));

			res = (AddressBook) ois.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}

	
	
	
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("ContactData.ser"));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compare(Contacts c1, Contacts c2) {
		return c1.getLastName().toLowerCase().compareTo(c2.getLastName().toLowerCase());
	}


	public String size_great() {
		return "Hello! You have "+Integer.toString(contacts.size())+" "+"contacts in your addessbook.";
		
	}


}