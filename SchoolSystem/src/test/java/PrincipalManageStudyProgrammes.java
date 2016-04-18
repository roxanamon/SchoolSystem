import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.ArrayList;
import otherclasses.Course;
import otherclasses.Exam;
import otherclasses.Principal;
import otherclasses.School;
import otherclasses.Student;
import otherclasses.StudyProgramme;
import otherclasses.Teacher;

//först ska vi skapa en virtual och en example of school för att kunna testa det.


public class PrincipalManageStudyProgrammes {
	
	
	 private static School school;
	

	@Before
	public void setUpBeforeClass() throws Exception {
		school = new School("Solnademin", "770301-1119", "John", "Smith");
	}

	@Test
	public void test() {
		Principal principal = school.getPrincipal();
		StudyProgramme programme = new StudyProgramme(school, "Test15", null, 160, 15, null );
		principal.addStudyProgramme (programme);
		
		Assert.assertEquals("Unexpected number of study programmes!", 1, school.getStudyProgrammes().size());
		
	}
	
	@Test
	public void delete() {        

		Principal principal = school.getPrincipal();		
		principal.removeStudyProgramme ("Test15");	
		
		Assert.assertEquals("Failed to delete 'Test15'!", 0, school.getStudyProgrammes().size());
		
	}
	
	@Test
	public void addCourse() {
		Principal principal = school.getPrincipal();
		Course course = new Course("workshop", 76);
		school.addCourse(course); 
		
		Assert.assertEquals("Failure! The teacher could not be added!", 0 , school.getTeachers().size());
		
		}
	
	
	@Test
	public void addTeacher() {
		Principal principal = school.getPrincipal();
		Teacher teacher = new Teacher(school, "770301-1119", "Alen", "Smith" );
		principal.addTeacherToSchool (teacher);
		
		Assert.assertEquals("Failure! The teacher could not be added!", 1 , school.getTeachers().size());
		
	}
	
	
	@Test
	public void addCourseToProgramme(){
		Principal principal = school.getPrincipal();
		StudyProgramme programme = new StudyProgramme (school, "Test15", null , 160, 15, null);
		principal.addStudyProgramme(programme);
		
		Course course1 = new Course ("TestAutomation", 55);
		principal.addCourseToSchool(course1);
		Assert.assertEquals("Unexpected number of courses in school" + school.getSchoolName(),  1, school.getCourses().size());
		
		principal.addCourseToStudyProgramme(programme.getName(), course1.getName());
		
		Assert.assertEquals("Unexpected number of courses in programme" + programme.getName(),  1, programme.getCourseNames().size());
		Assert.assertEquals("Unexpected course in programme " + course1.getName(), course1.getName(), programme.getCourseNames().get(0));
		
		school.removeCourse("TestAutomation");
		
		Assert.assertEquals("Could not remove course TestAutomation from school"+ course1.getName(),1,programme.getCourseNames().size()  );
		
		
		
		principal.removeCourseFromStudyProgramme("Test15", "TestAutomation");
		
		Assert.assertEquals("Could not remove course TestAutomation from StudyProgramme"+ course1.getName(),0,programme.getCourseNames().size()  );

	}
	
	
	@Test
	public void addStudent(){
		Principal principal = school.getPrincipal();
		Student student1 = new Student(school, "800331-6364", "Alen", "Smith");
		school.addStudent(student1);
		
		Student student2 = new Student(school, "720920-5900", "Roz", "Berg");
		school.addStudent(student2);
				
		Assert.assertEquals("Failure! The student could not be added!", 2 , school.getStudents().size());
		
		
		school.removeStudent("800331-6364");
		
		//Assert.assertEquals("Failure! The student could not be deleted!", 0 , school.getStudents().size());
		
		assertNull("The student could not be deleted! " + student1.getFirstName(), school.getStudentByPersonalNumber(student1.getPersonalNumber().getNumber()));
		//Assert.assertEquals("The student could not be deleted! " + student1.getFirstName(), "Alen", school.getStudentByPersonalNumber(student1.getPersonalNumber().getNumber()));
		
		assertNotNull("Student deleted!"+ student2.getPersonalNumber(), school.getStudentByPersonalNumber(student2.getPersonalNumber().getNumber()));
		
	}
	
	@Test
	public void assignTeacher(){
		Principal principal = school.getPrincipal();
		Teacher teacher = new Teacher(school, "770301-1119", "Alen", "Smith" );
		principal.addTeacherToSchool (teacher);
		teacher.getPersonalNumber().getNumber();
		
		Course course1 = new Course ("TestAutomation", 55);
		principal.addCourseToSchool(course1);

		
		principal.assignTeacherToCourse("770301-1119", "TestAutomation");
		
		boolean schoolTeacherFound;
		for(String courseTeacherPnr : course1.getTeachers()) {
			schoolTeacherFound = false;
			for(Teacher schoolTeacher : school.getTeachers()) {
				if(schoolTeacher.getPersonalNumber().getNumber().equals(courseTeacherPnr)) {
					schoolTeacherFound = true;
					break;
				}
			}
			if(schoolTeacherFound == false) {
				Assert.fail("Teacher in course is no teacher of the school!");
			}
		}
		Assert.assertEquals("Failure! The teacher could not be assigned!", 1, course1.getTeachers().size());
	}
	}

	
		
		
		

	
	


