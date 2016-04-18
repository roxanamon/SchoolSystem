package otherclasses;

public class Person {
	//Attributes
	private PersonalNumber personalNumber;
	private String firstName ="";
	private String lastName="";

	//Constructor
	public Person( String personalNumber, String firstName, String lastName ) {
		
		setPersonalNumber(new PersonalNumber(personalNumber));
		setFirstName(firstName);
		setLastName(lastName);
	}

	public boolean isFemale() {
		return personalNumber.isFemale();
	}
	public boolean isMale() {
		return !isFemale();
	}
	public int getAge() {
		return personalNumber.getAge();
	}
	
	//getters and setters
	public PersonalNumber getPersonalNumber() {
		return this.personalNumber;
	}
	private void setPersonalNumber(PersonalNumber pnr) {
		this.personalNumber = pnr;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
