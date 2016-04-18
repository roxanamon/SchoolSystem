import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Test;

import otherclasses.*;
import otherclasses.Student;
import otherclasses.StudyFee;
import paymentapp.CreditCard;
import paymentapp.CreditCardType;

public class PaymentTest {
	
	private static School school = new School ("asda", "770302-7131", "3", "5");
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		
		int year = 2016;
		
		Student student1 = new Student (school, "770302-7131", "J", "S");
		Student student2 = new Student (school, "800101-0001", "Mary", "Smith");
		Student student3 = new Student (school, "800102-0000", "Jane", "Smith");


		StudyFee fee = new StudyFee(2016, 20000f);
		Principal principal = school.getPrincipal();
		principal.applyStudyFees(fee);
		
		Assert.assertEquals("Unexpected number of sutdents in not-paid-yet list", 3, principal.getListOfStudentsWhoHaveNotPaidByYear(year).size());	
		student1.payStudyFee(year, new CreditCard("J S", CreditCardType.Visa,"1234123412341234", LocalDateTime.now().plusDays(1),"123"));
		
		Assert.assertEquals("Unexpected number of sutdents in not-paid-yet list", 2, principal.getListOfStudentsWhoHaveNotPaidByYear(year).size());
		student2.payStudyFee(year, new CreditCard("J S", CreditCardType.Visa,"1111222233334444", LocalDateTime.now().plusDays(1),"123"));

	}

}
