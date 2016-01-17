
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
/**
 * 
 * @author kiarie Ndegwa u4742829
 * comp6442: Assignment1
 * Contact class:
 * Contains attribute associated with each contact
 * i.e. FirstName, LastName, phone number and e-mail
 *
 */
public class Contacts implements Serializable{

	private String FirstName;
	private String LastName;
	private String phone;
	private String mail;
	/**
	 Simple contacts class
	 ***/

	public Contacts(String FirstName, String LastName, String phone, String mail){
			this.FirstName =FirstName;
			this.LastName = LastName;
			this.phone = phone;
			this.mail = mail;
			
		}
	
	public Contacts(){
		
	}

		public String getFirstName(){
			return this.FirstName;
		}
		
		public String getLastName(){
			return this.LastName;
		}
		
		public String getPhone(){
			return this.phone;
		}
		public String getMail(){
			return this.mail;
		}
		
		public void setLastName(String l){
			this.LastName =l;
		}
		public void setFirstName(String l){
			this.FirstName =l;
		}
		public void setPhone(String l){
			this.phone =l;
		}
		
		public void setMail(String l){
			this.mail =l;
		}
		@Override
		public String toString(){
			return this.LastName+" "+this.FirstName+" "+this.phone; 
		}
}


