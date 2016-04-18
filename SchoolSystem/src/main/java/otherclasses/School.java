package otherclasses;

import java.util.ArrayList;
import java.util.Hashtable;

import paymentapp.CreditCard;
import paymentapp.PaymentApp;
import paymentapp.PaymentApp.ResponseCode;

public class School {

	private String schoolName;
	private Principal principal;
	private ArrayList<StudyProgramme> studyProgrammes;
	private ArrayList<Teacher> teachers;
	private ArrayList<Course> courses;
	private Hashtable<String, Student> students;
	private ArrayList<StudyFee> studyFees;
	private PaymentApp paymentApp;

	public School(String schoolName, String principalPersonalNumber, String principalFirstName, String principalLastName) {

		setSchoolName(schoolName);
		setPrincipal(new Principal(this, principalPersonalNumber, principalFirstName, principalLastName));
		setStudyProgrammes(new ArrayList<StudyProgramme>());
		setTeachers(new ArrayList<Teacher>());
		setCourses(new ArrayList<Course>());
		setStudents(new Hashtable<String, Student>());
		setStudyFees(new ArrayList<StudyFee>());
		setPaymentApp(new PaymentApp());
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public void addStudyProgramme(StudyProgramme programme) {
		studyProgrammes.add(programme);
	}

	public boolean removeStudyProgramme(String programmeName) {
		boolean removedProgramme = false;
		for(int i = 0; i < studyProgrammes.size(); i++) {
			if(studyProgrammes.get(i).getName().equals(programmeName)) {
				studyProgrammes.remove(i);
				removedProgramme = true;
			}
		}
		return removedProgramme;
	}

	public ArrayList<StudyProgramme> getStudyProgrammes() {
		return studyProgrammes;
	}

	public void setStudyProgrammes(ArrayList<StudyProgramme> studyProgrammes) {
		this.studyProgrammes = studyProgrammes;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse( Course course ) {
		this.courses.add(course);
	}
	
	public boolean removeCourse( String courseName ) {
		
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i).getName().equals(courseName)) {
				
				courses.remove(i);
				return true;
			}
		}
		return false;
	}

	public void addCourseToStudyProgramme(String programmeName, String courseName) {

		boolean foundProgramme = false;
		for(StudyProgramme programme : this.studyProgrammes ) {

			if(programme.getName().equals(programmeName)) {

				programme.addCourse(courseName);
				foundProgramme = true;
			}
		}

		if(foundProgramme == false) {

			throw new RuntimeException("Failed to add course to study programme because no study programme exists with the name " + programmeName);
		}
	}

	public void removeCourseFromStudyProgramme(String programmeName, String courseName) {

		boolean foundProgramme = false;
		boolean removedCourse = false;
		for(StudyProgramme programme : this.studyProgrammes ) {

			if(programme.getName().equals(programmeName)) {

				removedCourse = programme.removeCourse(courseName);				
				foundProgramme = true;
			}
		}

		if(foundProgramme == false) {

			throw new RuntimeException("Failed to remove course from study programme because no study programme exists with the name " + programmeName);
		}
		if(removedCourse == false) {

			throw new RuntimeException("Failed to remove course " + courseName + " from study programme " + programmeName + "!");
		}
	}

	public void addStudent(Student student)	{
		this.students.put(student.getPersonalNumber().getNumber(), student);
	}

	public boolean removeStudent(String personalNumber) {
		
		Student student = students.get(personalNumber);
		
		if(student ==null){
			return true;
		}
		
		students.remove(personalNumber);
		
		return true;
		
	}
	
	
	public Hashtable<String, Student> getStudents() {
		return students;
	}

	public void setStudents(Hashtable<String, Student> students) {
		this.students = students;
	}

	public Student getStudentByPersonalNumber(String personalNumber) {
		return this.students.get(personalNumber);
	}

	public ArrayList<StudyFee> getStudyFees() {
		return studyFees;
	}

	public void setStudyFees(ArrayList<StudyFee> studyFees) {
		this.studyFees = studyFees;
	}
	
	public void applyStudyFee(StudyFee fee) {
		this.studyFees.add(fee);
	}

	public ResponseCode payStudyFee(int year, String personalNumber, CreditCard creditCard) {

		ResponseCode returnCode = null;
		for(StudyFee fee : studyFees) {
			
			if(fee.getYear() == year) {
				returnCode = paymentApp.makeCreditCardPayment(creditCard, fee.amount);
				if(returnCode.getId() == ResponseCode.Success.getId())
				{
					fee.addStudentWhoHasPaid(personalNumber);
				}
			}
		}

		return returnCode;
	}

	public PaymentApp getPaymentApp() {
		return paymentApp;
	}

	public void setPaymentApp(PaymentApp paymentApp) {
		this.paymentApp = paymentApp;
	}
}
