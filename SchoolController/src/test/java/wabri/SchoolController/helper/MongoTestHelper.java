package wabri.SchoolController.helper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoTestHelper {
	// to add elements in the students table for testing
	private DBCollection students;

	public MongoTestHelper(MongoClient mongoClient) {
		// make sure to drop the students table for testing
		DB db = mongoClient.getDB("school");
		db.getCollection("student").drop();
		students = db.getCollection("student");
	}

	public void addStudent(String id, String name) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		students.insert(document);
	}

	public boolean containsStudent(String id, String name) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		return students.find(query).hasNext();
	}

}
