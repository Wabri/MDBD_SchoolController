package wabri.SchoolController.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

import wabri.SchoolController.Student;
import wabri.SchoolController.helper.MongoTestHelper;
import wabri.SchoolController.mongo.MongoDatabaseWrapper;

public abstract class AbstractMongoDatabaseWrapperTest {

	private MongoDatabaseWrapper mongoDatabase;

	public abstract MongoClient createMongoClient() throws UnknownHostException;

	private MongoTestHelper mongoTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		MongoClient mongoClient = createMongoClient();
		mongoTestHelper = new MongoTestHelper(mongoClient);
		mongoDatabase = new MongoDatabaseWrapper(mongoClient);
	}

	@Test
	public void testGetAllStudentsEmpty() {
		assertTrue(mongoDatabase.getAllStudentsList().isEmpty());
	}

	@Test
	public void testGetAllStudentsNotEmpty() {
		mongoTestHelper.addStudent("1", "first");
		mongoTestHelper.addStudent("2", "second");
		assertEquals(2, mongoDatabase.getAllStudentsList().size());
	}

	@Test
	public void testFindStudentByIdNotFound() {
		mongoTestHelper.addStudent("1", "first");
		assertNull(mongoDatabase.findStudentById("2"));
	}

	@Test
	public void testFindStudentByIdFound() {
		mongoTestHelper.addStudent("1", "first");
		mongoTestHelper.addStudent("2", "second");
		Student findStudentById = mongoDatabase.findStudentById("2");
		assertNotNull(findStudentById);
		assertEquals("2", findStudentById.getId());
		assertEquals("second", findStudentById.getName());
	}

	@Test
	public void testStudentIsSaved() {
		mongoDatabase.save(new Student("1", "test"));
		assertTrue(mongoTestHelper.containsStudent("1", "test"));
	}

}