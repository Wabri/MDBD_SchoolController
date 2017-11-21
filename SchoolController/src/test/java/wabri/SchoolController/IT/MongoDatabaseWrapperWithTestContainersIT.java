package wabri.SchoolController.IT;

import java.net.UnknownHostException;

import org.junit.ClassRule;
import org.testcontainers.containers.GenericContainer;

import com.mongodb.MongoClient;

import wabri.SchoolController.common.AbstractMongoDatabaseWrapperTest;

public class MongoDatabaseWrapperWithTestContainersIT extends AbstractMongoDatabaseWrapperTest {

	@SuppressWarnings("rawtypes")
	@ClassRule
	public static GenericContainer mongo = new GenericContainer("mongo:latest").withExposedPorts(27017);

	@Override
	public MongoClient createMongoClient() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(mongo.getContainerIpAddress(), mongo.getMappedPort(27017));
		return mongoClient;
	}

}
