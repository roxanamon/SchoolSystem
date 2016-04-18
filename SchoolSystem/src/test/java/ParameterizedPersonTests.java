import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import otherclasses.Person;

@RunWith( value = Parameterized.class)
public class ParameterizedPersonTests {
	
	String firstName;	String lastName; 	int expAge;

	//parameters pass via this constructor
	public ParameterizedPersonTests( String firstName, String lastName, int expAge ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.expAge = expAge;
	}

	//Declares parameters here
	@Parameters(name = "{index}: {0},{1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ "John", "Smith", 35 }, 
			{ null, "Smith", 36 }, 
			{ "John", null, 35 }
		});
	}

	@Test
	public void testConstructors() {	
		Person person = new Person("800430-9350", firstName, lastName);
		assertEquals("Unexpected first name in new Person", firstName, person.getFirstName());
		assertEquals("Unexpected last name in new Person", lastName, person.getLastName());
		assertEquals("Unexpected age in new Person", expAge, person.getAge());
	}
}

