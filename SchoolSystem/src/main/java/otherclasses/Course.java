package otherclasses;

import java.util.ArrayList;

public class Course {

	private String name;
	private int points;
	private ArrayList<String> teachers;
	
	public Course( String courseName, int points ) {
		setName(courseName);
		setPoints(points);
		setTeachers(new ArrayList<String>());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void addTeacher(String personalNumber) {
		teachers.add(personalNumber);
	}
	public boolean removeTeacher(String personalNumber) {
		
		boolean removedTeacher = false;
		for(int i = 0; i < teachers.size(); i++) {
			
			if(teachers.get(i).equals(personalNumber)) {
				teachers.remove(i);
				removedTeacher = true;
				break;
			}
		}
		return removedTeacher;
	}

	public ArrayList<String> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<String> teachers) {
		this.teachers = teachers;
	}
}
