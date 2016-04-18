package otherclasses;

import java.util.ArrayList;

public class Principal extends Person {

	private School school;

	public Principal(School school, String personalNumber, String firstName, String lastName) {

		super(personalNumber, firstName, lastName);
		setSchool(school);
	}

	public void addStudyProgramme(StudyProgramme programme) {

		school.addStudyProgramme(programme);
	}

	public boolean removeStudyProgramme(String programmeName) {

		return school.removeStudyProgramme(programmeName);
	}

	public void addCourseToSchool( Course course ) {
		
		this.school.addCourse(course);
	}
	
	public boolean removeCourseFromSchool( String courseName ) {
		
		return this.school.removeCourse(courseName);
	}
	
	public void addCourseToStudyProgramme(String programmeName, String courseName) {

		school.addCourseToStudyProgramme(programmeName, courseName);
	}

	public void removeCourseFromStudyProgramme(String programmeName, String courseName) {

		school.removeCourseFromStudyProgramme(programmeName, courseName);
	}

	public void addTeacherToSchool(Teacher teacher) {

		school.addTeacher(teacher);
	}

	public void assignTeacherToCourse(String personalNumber, String courseName) {

		for(Course course : school.getCourses()) {
			if(course.getName().equals(courseName)) {
				course.addTeacher(personalNumber);
				break;
			}
		}
	}

	public boolean unassignTeacherFromCourse(String personalNumber, String courseName) {

		boolean teacherRemoved = false;
		for(Course course : school.getCourses()) {
			if(course.getName().equals(courseName)) {
				teacherRemoved = course.removeTeacher(personalNumber);
				break;
			}
		}
		return teacherRemoved;
	}

	public boolean removeStudent(String personalNumber) {

		return !school.removeStudent(personalNumber);
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void applyStudyFees(StudyFee fee) {
		school.applyStudyFee(fee);
	}

	public ArrayList<String> getListOfStudentsWhoHaveNotPaidByYear(int year) {

		ArrayList<String> badStudents = new ArrayList<String>();
		ArrayList<String> goodStudents = null;
		for(StudyFee fee : school.getStudyFees()) {
			if(fee.getYear() == year) {
				goodStudents = fee.getStudentsWhoHavePaid();
			}
		}
		if(goodStudents == null) {
			throw new RuntimeException("No study fees seem to have been applied to year " + year);
		}

		for(Student student : school.getStudents().values()) {
			if(goodStudents.contains(student.getPersonalNumber().getNumber())) {
				System.out.println(student.getFirstName() + " " + student.getLastName() + " is a good student, " + 
						(student.getPersonalNumber().isFemale() ? "she " : "he ") + "has paid the fee for year " + year + "!");
			}
			else {
				System.out.println(student.getFirstName() + " " + student.getLastName() + " is a bad student, " + 
						(student.getPersonalNumber().isFemale() ? "she " : "he ") + "has not paid the fee for year " + year + "!");
				badStudents.add(student.getPersonalNumber().getNumber());
			}
		}
		
		return badStudents;
	}
}
