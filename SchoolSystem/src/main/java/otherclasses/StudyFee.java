package otherclasses;

import java.util.ArrayList;

public class StudyFee {

	int year;
	float amount;
	ArrayList<String> studentsWhoHavePaid;
	
	public StudyFee(int year, float amount) {
		setYear(year);
		setAmount(amount);
		setStudentsWhoHavePaid(new ArrayList<String>());
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public ArrayList<String> getStudentsWhoHavePaid() {
		return studentsWhoHavePaid;
	}

	public void setStudentsWhoHavePaid(ArrayList<String> studentsWhoHavePaid) {
		this.studentsWhoHavePaid = studentsWhoHavePaid;
	}
	
	public void addStudentWhoHasPaid(String personalNumber) {
		this.studentsWhoHavePaid.add(personalNumber);
	}
}
