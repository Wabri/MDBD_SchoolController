package wabri.SchoolController.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

import wabri.SchoolController.Database;
import wabri.SchoolController.SchoolController;
import wabri.SchoolController.Student;
import wabri.SchoolController.helper.MongoTestHelper;
import wabri.SchoolController.mongo.MongoDatabaseWrapper;

public class SchoolControllerIT {
	// SUT
	private SchoolController schoolController;
	// helper for testing with Mongo
	private MongoTestHelper mongoTestHelper;

	@Before
	public void setUp() throws Exception {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		mongoTestHelper = new MongoTestHelper(mongoClient);
		// we don't mock the database:
		// we use a real instance of MongoDatabaseWrapper
		Database database = new MongoDatabaseWrapper(mongoClient);
		schoolController = new SchoolController(database);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Student> allStudents = schoolController.getAllStudents();
		assertEquals(0, allStudents.size());
	}

	@Test
	public void testGetAllStudentsWhenThereIsOneStudent() {
		mongoTestHelper.addStudent("1", "test");
		List<Student> allStudents = schoolController.getAllStudents();
		assertEquals(1, allStudents.size());
	}

	@Test
	public void testGetStudentByIdWhenStudentIsNotThere() {
		mongoTestHelper.addStudent("1", "test");
		Student student = schoolController.getStudentById("2");
		assertNull(student);
	}

	@Test
	public void testGetStudentByIdWhenStudentIsThere() {
		mongoTestHelper.addStudent("1", "test");
		Student student = schoolController.getStudentById("1");
		assertNotNull(student);
		assertEquals("test", student.getName());
	}

}
