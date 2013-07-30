package org.adclear.dbunit.json;

import java.net.UnknownHostException;

import com.mongodb.*;
import lombok.Getter;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.database.IDatabaseConnection;

/**
 * The {@link AbstractDatabaseTester} implementation for mongodb.
 * 
 * @author fit
 * 
 */
@Getter
public class MongoDatabaseTester extends AbstractDatabaseTester {

	private MongoClient mongo;

	private MongoClientURI mongoURI;

	private DB db;

	public MongoDatabaseTester(MongoClientURI uri) throws MongoException, UnknownHostException {
		this.mongoURI = uri;
		this.mongo = new MongoClient(uri);
		this.db = mongo.getDB(mongoURI.getDatabase());
	}

    public MongoDatabaseTester(MongoClient mongo, DB db){
        this.mongo = mongo;
        this.db = db;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dbunit.IDatabaseTester#getConnection()
	 */
	@Override
	public IDatabaseConnection getConnection() throws Exception {
		return new MongoDatabaseConnection(mongo, db);
	}
}
