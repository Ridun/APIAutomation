package pojo;

public class Getcourse {
	private String url;
	private String services;
	private String expertise;	
	private Courses Courses;
	private String instructor;
	private String linkedIn;
	
	//alt+shift+s --to create getters and setters automatically in eclipse
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojo.Courses getCourses() {
		return Courses;
	}
	public void setCourses(pojo.Courses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedin() {
		return linkedIn;
	}
	public void setLinkedin(String linkedin) {
		this.linkedIn = linkedin;
	}
}

