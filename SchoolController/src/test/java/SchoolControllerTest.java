import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import wabri.SchoolController.Database;
import wabri.SchoolController.SchoolController;
import wabri.SchoolController.Student;

public class SchoolControllerTest {
	private Database database;
	private List<Student> students;
	private SchoolController schoolController;

	@Before
	public void setUp() throws Exception {
		database = mock(Database.class);
		schoolController = new SchoolController(database);
		students = new ArrayList<Student>();
		when(database.getAllStudentsList()).thenReturn(students);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Student> allStudents = schoolController.getAllStudents();
		verify(database).getAllStudentsList();
		assertEquals(0, allStudents.size());
	}

	@Test
	public void testGetAllStudentsWhenThereIsOneStudent() {
		students.add(new Student());
		List<Student> allStudents = schoolController.getAllStudents();
		verify(database).getAllStudentsList();
		assertEquals(1, allStudents.size());
	}

	@Test
	public void testGetStudentByIdWhenStudentIsNotThere() {
		students.add(new Student("1", "test"));
		Student student = schoolController.getStudentById("2");
		verify(database).findStudentById("2");
		assertNull(student);
	}

	@Test
	public void testGetStudentByIdWhenStudentIsThere() {
		students.add(new Student("1", "test"));
		when(database.findStudentById("1")).thenReturn(students.get(0));
		Student student = schoolController.getStudentById("1");
		verify(database).findStudentById("1");
		assertNotNull(student);
	}

	@Test
	public void testSaveStudentWhenStudentDoesNotAlreadyExist() {
		Student student = new Student("1", "test");
		when(database.findStudentById("1")).thenReturn(null);
		assertTrue(schoolController.addStudent(student));
		verify(database).findStudentById("1");
		verify(database).save(student);
	}

	@Test
	public void testSaveStudentWhenStudentAlreadyExists() {
		Student student = new Student("1", "test");
		when(database.findStudentById("1")).thenReturn(new Student("1", "name"));
		assertFalse(schoolController.addStudent(student));
		verify(database).findStudentById("1");
		verifyNoMoreInteractions(database);
	}
}
