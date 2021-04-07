package project.BankManagementSystemconnection;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

public class Connection {
	
	private static Cluster cluster;
	
	public static Cluster getConnection() {
		if(cluster==null) {
			  CouchbaseEnvironment envr = DefaultCouchbaseEnvironment.builder().connectTimeout(1000).build();
			  Cluster cluster = CouchbaseCluster.create(envr,"127.0.0.1");
		      cluster.authenticate("root" , "root265");
		      return cluster;
		}
		return cluster;
	}
}
