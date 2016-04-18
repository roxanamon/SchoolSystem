package otherclasses;

public class Exam {

	private String courseName;
	private float grade;
	
	public Exam(String courseName, float grade) {
		setCourseName(courseName);
		setGrade(grade);
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
}
