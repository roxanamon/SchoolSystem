package otherclasses;


import java.text.DecimalFormat;
import java.util.ArrayList;

import paymentapp.CreditCard;
import paymentapp.PaymentApp.ResponseCode;

public class Student extends Person {
	//Attributes
	private School school;
	private StudyProgramme studyProgramme;
	private ArrayList<Exam> takenExams;

	//Constructors
	public Student(School school, String personalNumber, String firstName, String lastName) {
		super(personalNumber, firstName, lastName);
		takenExams = new ArrayList<Exam>();
		setSchool(school);
		school.addStudent(this);
	}		

	public Student(School school, String personalNumber, String firstName, String lastName, ArrayList<Exam> previouslyTakenExams) {
		this(school, personalNumber, firstName, lastName);
		takenExams.addAll(previouslyTakenExams);
	}

	public boolean signUpForStudyProgramme(String nameOfStudyProgramme) {

		boolean signedUp = false;
		for(StudyProgramme programme : this.school.getStudyProgrammes()) {

			if(programme.getName().equals(nameOfStudyProgramme)) {

				signedUp = programme.addStudent(this.getPersonalNumber().getNumber());
				if(signedUp == true) {
					
					setStudyProgramme(programme);
					break;
				}
			}
		}
		return signedUp;
	}

	public float calculateAverageGrade(){

		if(takenExams.size() == 0) {
			return 0;
		}

		float sum = 0f;
		for(Exam exam : takenExams) {
			sum += exam.getGrade();
		}

		return sum / takenExams.size();
	}

	public boolean hasClearedTheProgramme() {

		int numberOfPointsReached = getReachedNumberOfStudyPoints();

		if (this.calculateAverageGrade() >= studyProgramme.getRequiredAverageGrade() && numberOfPointsReached >= studyProgramme.getRequiredPoints()){
			return true;
		}
		return false;
	}

	public ResponseCode payStudyFee(int year, CreditCard creditCard) {

		return school.payStudyFee(year, this.getPersonalNumber().getNumber(), creditCard);
	}

	public int getReachedNumberOfStudyPoints() {

		int numberOfPointsReached = 0;

		for( Exam exam : takenExams ) {

			for( Course course : school.getCourses() ) {

				if(course.getName().equals(exam.getCourseName())) {

					numberOfPointsReached += course.getPoints();
					break;
				}
			}
		}

		return numberOfPointsReached;
	}

	public ArrayList<Course> listAllCourses() {
		
		ArrayList<Course> allCourses = new ArrayList<Course>();
		for( Course course : school.getCourses() ) {

			if(studyProgramme.getCourseNames().contains( course.getName())) {
				allCourses.add(course);
			}
		}
		
		return allCourses;
	}
	
	//ToString()
	public String toString() {
		calculateAverageGrade();
		DecimalFormat df = new DecimalFormat("#.0");

		StringBuilder stringBuilder = new StringBuilder("Student: " + getFirstName() + " " + getLastName() + System.lineSeparator() + "Grades: ");
		for( Exam exam : takenExams ) {
			stringBuilder.append(exam.getCourseName() + ": " + df.format(exam.getGrade()) + System.lineSeparator());
		}
		stringBuilder.append("Final grade: " + df.format(this.calculateAverageGrade()) + System.lineSeparator());
		if(hasClearedTheProgramme()) {
			stringBuilder.append("The student has cleared the course" + System.lineSeparator());
		}
		else {
			stringBuilder.append("The student has not cleared the course" + System.lineSeparator());
		}

		return stringBuilder.toString();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public ArrayList<Exam> getTakenExams() {
		return takenExams;
	}

	public void setTakenExams(ArrayList<Exam> takenExams) {
		this.takenExams = takenExams;
	}

	public void addTakenExam(Exam exam) {
		this.takenExams.add(exam);
	}

	public StudyProgramme getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgramme studyProgramme) {
		this.studyProgramme = studyProgramme;
	}
}