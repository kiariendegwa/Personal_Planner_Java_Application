import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kiarie Ndegwa u4742829
 * Performs junit testing on addressbook
 *
 */


public class addressbook_junit_test{
	
	@Test
	public void testIdentity(){ 
		//passes every test case
		AddressBook test = new AddressBook();
		Contacts test1 = new Contacts();
		test1.setFirstName("Salvador");
		test1.setLastName("Dali");
		test1.setPhone("0300-314-314");
		
		test.add(test1);
		assertEquals(test.get().toArray().length, 1);
		test.remove(test1);
		assertEquals(test.get().toArray().length, 0);
		
	
	}
	
}