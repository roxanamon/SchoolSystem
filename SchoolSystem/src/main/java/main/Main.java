package main;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Assert;

import otherclasses.Course;
import otherclasses.Exam;
import otherclasses.Principal;
import otherclasses.School;
import otherclasses.Student;
import otherclasses.StudyFee;
import otherclasses.StudyProgramme;
import otherclasses.Teacher;
import paymentapp.CreditCard;
import paymentapp.CreditCardType;
import paymentapp.PaymentApp.ResponseCode;

public class Main {	
	public static void main(String[] args) {
		
		School school = new School("Solnademin", "800331-5374", "John", "Smith");
		Principal principal = school.getPrincipal();
		
		Course course1 = new Course("JavaProgramming", 60);
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(course1);
		courses.add(new Course("TestAutomation", 55));
		courses.add(new Course("TestTheory", 45));
		school.setCourses(courses);
		
		ArrayList<String> courseNames = new ArrayList<String>();
		for(Course course : courses) {
			courseNames.add(course.getName());
		}
		
		StudyProgramme studyProgramme = new StudyProgramme(school, "TEST15", courseNames, 160, 15, new ArrayList<String>());
		principal.addStudyProgramme(studyProgramme);
		
		Teacher teacher = new Teacher(school, "800331-6364", "Joanna", "Smith");
		principal.addTeacherToSchool(teacher);
		principal.assignTeacherToCourse(teacher.getPersonalNumber().getNumber(), course1.getName());
		
		Student student1 = new Student(school, "800331-6364", "Joanna", "Smith");
		Assert.assertTrue("Unexpected gender of student1!", student1.getPersonalNumber().isFemale());
		Assert.assertTrue("Unexpectedly failed to add student to study programme!", student1.signUpForStudyProgramme(studyProgramme.getName()));

		Assert.assertEquals("Unexpected number of ungraded students for course " + course1.getName(), 1, teacher.getUngradedStudentsByCourse(course1.getName()).size());
		
		float grade = 8.9f;
		teacher.gradeStudent(course1.getName(), student1.getPersonalNumber().getNumber(), grade);
		Assert.assertEquals("Unexpected average grade!", grade, student1.calculateAverageGrade(), 0f);
		Assert.assertFalse("Student has unexpectedly cleared the course!", student1.hasClearedTheProgramme());
		
		Assert.assertEquals("Unexpected number of ungraded students for course " + course1.getName(), 0, teacher.getUngradedStudentsByCourse(course1.getName()).size());
		
		studyProgramme.printFullRelatory();

		int yearToBePaid = 2016;
		principal.applyStudyFees(new StudyFee(yearToBePaid, 10000f));
		Assert.assertEquals("Unexpected number of students who have not paid!", 1, principal.getListOfStudentsWhoHaveNotPaidByYear(yearToBePaid).size());;
		
		CreditCard creditCard = new CreditCard("Joanna S", CreditCardType.Visa, "1233211234121233", LocalDateTime.now().plusMonths(1L), "216");
		Assert.assertEquals("Credit card payment failed!", ResponseCode.Success.getId(), student1.payStudyFee(yearToBePaid, creditCard).getId());
		
		Assert.assertEquals("Unexpected number of students who have not paid!", 0, principal.getListOfStudentsWhoHaveNotPaidByYear(yearToBePaid).size());;
		
		System.out.println("Finished!");
	}

}
