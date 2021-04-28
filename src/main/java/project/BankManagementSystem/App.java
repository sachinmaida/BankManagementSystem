package project.BankManagementSystem;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

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

public class App {
	public static void main(String[] args) throws IOException {
		Customer customer = new Customer();

		Vertx vertx = Vertx.vertx();
		HttpServer httpServer = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.post("/register").handler(BodyHandler.create()).handler(routingContext -> {
			final Customer cust = Json.decodeValue(routingContext.getBody(), Customer.class);

			Firestore db;
			try {
				db = Connection.getConnection();
				DocumentReference docRef = db.collection("Client").document(cust.getUsername()+cust.getPassword());
				ApiFuture<WriteResult> result = docRef.set(cust);
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			response.end("Client added successfully");

		});

		router.post("/login").handler(BodyHandler.create()).handler(routingContext -> {
			final Login login = Json.decodeValue(routingContext.getBody(), Login.class);

			Firestore db;
			try {
				db = Connection.getConnection();
			DocumentReference docRef = db.collection("Client").document(login.getUsername()+login.getPassword());
	    	ApiFuture<DocumentSnapshot> future = docRef.get();
	    	DocumentSnapshot document = future.get();
	    	if (document.exists()) {
	    		customer.convert(document.toObject(Customer.class)) ;
	    		HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Login successfully");
	    	} else {
	    		HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Invalid Credential");
	    }
			} catch (IOException | InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		
		router.post("/update").handler(BodyHandler.create()).handler(routingContext -> {
			final Customer customer1;
			Boolean isAuthenticated=customer.getName()=="";

			if (isAuthenticated) {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Client Not logged In ");
			} else {

				customer1 = Json.decodeValue(routingContext.getBody(), Customer.class);
				Firestore db;
				try {
					db = Connection.getConnection();
					db.collection("Client").document(customer.getUsername()+customer.getPassword()).delete();
					customer.convert(customer1);
					db.collection("Client").document(customer.getUsername()+customer.getPassword()).set(customer);				

				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Details updated successfully");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		router.get("/logout").handler(routingContext -> {

			Boolean isAuthenticated=customer.getName()=="";

			if (isAuthenticated){

				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Client Not logged In ");

			} else {

				customer.convert(new Customer());
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Logout successfully ");

			}
		});
//
//		
		router.post("/applyforloan").handler(BodyHandler.create()).handler(routingContext -> {
			Boolean isAuthenticated=customer.getName()!="";

			if (isAuthenticated){
				final LoanDetails loan = Json.decodeValue(routingContext.getBody(), LoanDetails.class);
				if(loan.getLoanType()=="CarLoan")
					loan.setRateOfInterest("7");
				else if(loan.getLoanType()=="BusinessLoan")
					loan.setRateOfInterest("5");
				else
					loan.setDate("4");

				Firestore db;
				try {
					db = Connection.getConnection();
				db.collection("Loan").document(customer.getAccountNumber()).set(loan);			
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Loan Applied Successfully");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("Client Not logged In ");
				
			}
			

		});

		router.get("/viewloandetails").handler(BodyHandler.create()).handler(routingContext -> {
			Boolean isAuthenticated=customer.getName()!="";

			if (isAuthenticated) {
				Firestore db;
				try {
					db = Connection.getConnection();
				
				DocumentReference docRef=db.collection("Loan").document(customer.getAccountNumber());
				ApiFuture<DocumentSnapshot> future= docRef.get();
				DocumentSnapshot document = future.get();
				
				if(document.exists()) {
					
					HttpServerResponse response = routingContext.response();
					response.setChunked(true);
					LoanDetails loan=new LoanDetails();
					loan.convert(document.toObject(LoanDetails.class));
					response.end(Json.encodePrettily(loan));
					
				}
				} catch (IOException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				HttpServerResponse response = routingContext.response();
				response.setChunked(true);
				response.end("User Not logged In ");
				
			}
		});

		httpServer.requestHandler(router::accept).listen(8081);
		System.in.read();

	}

}
