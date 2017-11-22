package wabri.SchoolController.IT;

import java.net.UnknownHostException;
import wabri.SchoolController.common.AbstractMongoDatabaseWrapperTest;
import com.mongodb.MongoClient;

public class MongoDatabaseWrapperIT extends AbstractMongoDatabaseWrapperTest {
	
	@Override
	public MongoClient createMongoClient() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient();
		return mongoClient;
	}

}
