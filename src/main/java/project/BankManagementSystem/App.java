package project.BankManagementSystem;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import project.BankManagementSystem.Model.Customer;
import project.BankManagementSystem.Model.LoanDetails;
import project.BankManagementSystem.Model.Login;
import project.BankManagementSystemconnection.Connection;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//       System.out.println( "Hello World!" );

		

		Customer customer = new Customer();

		Vertx vertx = Vertx.vertx();

		HttpServer httpServer = vertx.createHttpServer();

		Router router = Router.router(vertx);
		router.post("/register").handler(BodyHandler.create()).handler(routingContext -> {
			final Customer cust = Json.decodeValue(routingContext.getBody(), Customer.class);

			Cluster cluster = Connection.getConnection();
			Bucket bucket = cluster.openBucket("heroes");
					
			JsonObject json=cust.toJson();
			String uniquekey = cust.getUsername() + cust.getPassword();

			JsonDocument content = JsonDocument.create(uniquekey, json);
			bucket.upsert(content);

			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			response.end("Customer added successfully");

		});

		
		
		router.post("/login").handler(BodyHandler.create()).handler(routingContext -> {
			final Login login = Json.decodeValue(routingContext.getBody(), Login.class);

			Cluster cluster = Connection.getConnection();
			Bucket bucket = cluster.openBucket("heroes");

			String uniquekey = login.getUsername() + login.getPassword();
			final JsonDocument answer = bucket.get(uniquekey);
			JsonObject object = null;
			if (answer != null) {

				object = answer.content();
				customer.fromJson(object);
		}
			if (object != null) {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Login successfully");
			} else {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Invalid Credential");
			}

		});

		
		
		router.post("/update").handler(BodyHandler.create()).handler(routingContext -> {
			final Customer customer1;

			if (customer.getName() == "") {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Customer Not logged In ");
			} else {

				customer1 = Json.decodeValue(routingContext.getBody(), Customer.class);

				String uniquekey = customer.getUsername() + customer.getPassword();
				customer.convert(customer1);

				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Details updated successfully");

				Cluster cluster = Connection.getConnection();
				Bucket bucket = cluster.openBucket("heroes");

				JsonObject json =customer.toJson();
				JsonDocument content = JsonDocument.create(customer.getUsername() + customer.getPassword(), json);
				bucket.remove(uniquekey);

				bucket.upsert(content);

			}
		});

		
		router.get("/logout").handler(routingContext -> {

			if (customer.getName() == "") {

				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("User Not logged In ");

			} else {

				customer.convert(new Customer());
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Logout successfully ");

			}
		});

		
		router.post("/applyforloan").handler(BodyHandler.create()).handler(routingContext -> {
			if(customer.getName() != "") {
				final LoanDetails loan = Json.decodeValue(routingContext.getBody(), LoanDetails.class);
				if(loan.getLoanType()=="CarLoan")
					loan.setRateOfInterest("7");
				else if(loan.getLoanType()=="BusinessLoan")
					loan.setRateOfInterest("5");
				else
					loan.setDate("4");

				Cluster cluster = Connection.getConnection();
				Bucket bucket = cluster.openBucket("Loan");
				
				JsonObject json = loan.toJson();
				String uniquekey = customer.getAccountNumber();

				JsonDocument content = JsonDocument.create(uniquekey, json);
				bucket.upsert(content);
				
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Loan Applied Successfully");
				
			}else {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("User Not logged In ");
				
			}
			

		});
		

		
		router.get("/viewloandetails").handler(BodyHandler.create()).handler(routingContext -> {
			if(customer.getName() != "") {
				Cluster cluster = Connection.getConnection();
				Bucket bucket = cluster.openBucket("Loan");
				
				String uniquekey = customer.getAccountNumber();
				
				final JsonDocument answer = bucket.get(uniquekey);
				if(answer!=null) {
					JsonObject object = answer.content();
					HttpServerResponse response = routingContext.response();
					response.setChunked(true);
					LoanDetails loan=new LoanDetails();
					loan.fromJson(object);
					response.end(Json.encodePrettily(loan));
					
				}
				
			}else {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("User Not logged In ");
				
			}
		});


		httpServer.requestHandler(router::accept).listen(8080);

	}

}
