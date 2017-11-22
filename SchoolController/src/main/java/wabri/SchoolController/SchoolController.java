package wabri.SchoolController;

import java.util.List;

public class SchoolController {
	private Database database;

	public SchoolController(Database database) {
		this.database = database;
	}

	public List<Student> getAllStudents() {
		return database.getAllStudentsList();
	}

	public Student getStudentById(String id) {
		return database.findStudentById(id);
	}

	public boolean addStudent(Student student) {
		if (getStudentById(student.getId()) != null)
			return false;

		database.save(student);
		return true;
	}

}
