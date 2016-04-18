package otherclasses;


import java.util.ArrayList;

public class StudyProgramme {
	
	private School school;
	private int requiredPoints;
	private float requiredAverageGrade = 6.0f;
	private String name = "";
	private int maxNumberOfStudents;
	private ArrayList<String> courseNames;
	private ArrayList<String> studentsPersonalNumbers;
		
	public StudyProgramme(School school, String nameOfStudyProgramme, ArrayList<String> courseNames, int requiredPoints, int maxNumberOfStudents, ArrayList<String> studentsPersonalNumbers){
		setSchool(school);
		setName(nameOfStudyProgramme);
		setCourses(courseNames);
		
		if (courseNames == null){
			setCourses(new ArrayList<String>());
		}
		else{
			setCourses(courseNames);
		}
		setRequiredPoints(requiredPoints);
		if(studentsPersonalNumbers == null){
			setStudents (new ArrayList<String>());
			
		}
		else{
			setStudents (studentsPersonalNumbers);
		}
	
		
		setRequiredPoints(requiredPoints);
		setMaxNumberOfStudents(maxNumberOfStudents);
		setStudents(studentsPersonalNumbers);
		
		
	}	
	
	public void addCourse(String courseName) {
		courseNames.add(courseName);
	}
	
	public boolean removeCourse(String courseName) {
		
		boolean courseRemoved = false;
		for(int i = 0; i < courseNames.size(); i++) {
			
			if(courseNames.get(i).equals(courseName)) {
				
				courseNames.remove(i);
				courseRemoved = true;
				break;
			}
		}
		
		return courseRemoved;
	}
	
	public boolean addStudent(String personalNumber) {
		if(studentsPersonalNumbers.size() < maxNumberOfStudents) {
			studentsPersonalNumbers.add(personalNumber);
			return true;
		}
		return false;
	}
	
	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}

	public ArrayList<String> getCourseNames() {
		return courseNames;
	}

	public void setCourses(ArrayList<String> courseNames) {
		this.courseNames = courseNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String classroomName) {
		this.name = classroomName;
	}

	public ArrayList<String> getStudentsPersonalNumbers() {
		return studentsPersonalNumbers;
	}

	public void setStudents(ArrayList<String> personalNumbers) {
		this.studentsPersonalNumbers = personalNumbers;
	}

	public void removeAStudent(String personalNumber) {
		
		boolean studentExists = false;
		for(int i= 0 ; i < this.studentsPersonalNumbers.size() ; i++) {
			
			if (studentsPersonalNumbers.get(i).equals(personalNumber)) {
				
				studentExists = true;
				this.studentsPersonalNumbers.remove(i);
				System.out.println("Student " + personalNumber + " successfully removed!");
				break;
			}	
		}
		
		if (!studentExists){
			System.out.println("The student does not exist!");
		}
	}
	
	public void printFullRelatory(){
		System.out.println("Name of study programme: " + this.name);
		System.out.println("Included courses:");
		for (String courseName : this.courseNames){
			System.out.println(courseName);
		}
		System.out.println("Attending students:");
		Student student;
		for (String personalNumber : this.studentsPersonalNumbers){
			
			student = this.school.getStudents().get(personalNumber);
			System.out.println(student.toString());
		}
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setCourseNames(ArrayList<String> courseNames) {
		this.courseNames = courseNames;
	}

	public void setStudentsPersonalNumbers(ArrayList<String> studentsPersonalNumbers) {
		this.studentsPersonalNumbers = studentsPersonalNumbers;
	}

	public int getMaxNumberOfStudents() {
		return maxNumberOfStudents;
	}

	public void setMaxNumberOfStudents(int maxNumberOfStudents) {
		this.maxNumberOfStudents = maxNumberOfStudents;
	}

	public float getRequiredAverageGrade() {
		return requiredAverageGrade;
	}

	public void setRequiredAverageGrade(float requiredAverageGrade) {
		this.requiredAverageGrade = requiredAverageGrade;
	}
}
