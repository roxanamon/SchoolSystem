package otherclasses;

import java.util.ArrayList;

public class Teacher extends Person {

	private School school;
	
	public Teacher(School school, String personalNumber, String firstName, String lastName ) {
		
		super(personalNumber, firstName, lastName);
		setSchool(school);
	}
	
	public boolean gradeStudent(String courseName, String personalNumberOfStudent, float grade) {
		
		Student student = school.getStudentByPersonalNumber(personalNumberOfStudent);
		Exam exam = new Exam(courseName, grade);
		student.addTakenExam(exam);
		return false;
	}
	
	public ArrayList<String> getUngradedStudentsByCourse(String courseName) {
		
		ArrayList<String> ungradedStudents = new ArrayList<String>();
		boolean curStudentHasAlreadyBeenGraded;
		for(StudyProgramme programme : school.getStudyProgrammes()) {
			
			for(String curCourseName : programme.getCourseNames()) {
				
				if(curCourseName.equals(courseName)) {
					
					for(String pnr : programme.getStudentsPersonalNumbers()) {
						curStudentHasAlreadyBeenGraded = false;
						
						for(Exam exam : school.getStudentByPersonalNumber(pnr).getTakenExams()) {

							if(exam.getCourseName().equals(courseName)) {
								curStudentHasAlreadyBeenGraded = true;
							}
						}
						
						if(curStudentHasAlreadyBeenGraded == false) {
							
							ungradedStudents.add(pnr);
						}
					}
					break;
				}
			}
		}
		
		return ungradedStudents;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
