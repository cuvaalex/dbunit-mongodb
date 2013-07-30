package org.adclear.dbunit.json;

import java.sql.Connection;
import java.sql.SQLException;

import com.mongodb.MongoClient;
import lombok.Getter;

import org.dbunit.database.AbstractDatabaseConnection;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;

@Getter
public class MongoDatabaseConnection extends AbstractDatabaseConnection {

	private MongoClient mongo;
	private DB db;

	/**
	 * Creates a new <codeMongoDatabaseConnectionM</code>.
	 * 
	 * @param mongo
	 *            the {@link MongoClient} instance that is used to load the test data
	 *            into
	 * @param db
	 *            the database in which the test data has to be loaded
	 */
	public MongoDatabaseConnection(MongoClient mongo, DB db) {
		this.mongo = mongo;
		this.db = db;
		getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
				new DefaultDataTypeFactory());
	}

	/**
	 * Perform close on the underlying {@link Mongo} instance. Is called from
	 * dbunit to cleanup all connections after finishing the loading of test
	 * data.
	 */
	@Override
	public void close() throws SQLException {
		mongo.close();
	}

	// ################## dummy implementations for AbstractDatabaseConnection
	// interface ###################
	/**
	 * (non-Javadoc)
	 * 
	 * Dummy implementation.
	 * 
	 * @return always null
	 * 
	 * @see org.dbunit.database.IDatabaseConnection#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * Dummy implementation.
	 * 
	 * @return always an empty string
	 * 
	 * @see org.dbunit.database.IDatabaseConnection#getSchema()
	 */
	@Override
	public String getSchema() {
		return "";
	}
}