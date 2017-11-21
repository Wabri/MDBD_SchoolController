package wabri.SchoolController.mongo;

import wabri.SchoolController.common.AbstractMongoDatabaseWrapperTest;
import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapperTest extends AbstractMongoDatabaseWrapperTest {
	@Override
	public MongoClient createMongoClient() {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		return mongoClient;
	}
}
