import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author kiarie Ndegwa u4742829
 * Junit testing for contacts class
 *
 */


public class contacts_junit_testing{
	
	@Test
	public void testIdentity(){ 
		//passes every test case
		Contacts test = new Contacts();
		test.setFirstName("Salvador");
		test.setLastName("Dali");
		test.setPhone("0300-314-314");
		
		assertEquals(test.getLastName(), "Dali");
		assertEquals(test.getFirstName(), "Salvador");
		assertEquals(test.getPhone(), "0300-314-314");
		assertEquals(test.toString(), "Dali Salvador 0300-314-314");
		
	}
	
	
}