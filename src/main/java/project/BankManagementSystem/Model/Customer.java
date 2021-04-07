package project.BankManagementSystem.Model;


import com.couchbase.client.java.document.json.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
private String name="";
	
	private String username;
	
	private String password;
	
	private String address;
	
	private String state;

	 private String country;
	 
	 private String email;
	 
	 private String pan;
	 
	 private String contactnumber;
	 
	 private String accounttype;
	 
	 private String accountNumber;
	 
	 
	 public JsonObject toJson() {
		 JsonObject json=JsonObject.empty()
					.put("username", username)
					.put("name", name)
					.put("password", password)
					.put("state", state)
					.put("country", country)
					.put("accountNumber", accountNumber)
					.put("accounttype", accounttype)
					.put("contactNumber", contactnumber)
					.put("address", address)
					.put("email", email)
					.put("pan", pan);
		 return json;
		 
	 }
	 
	 public void fromJson(JsonObject object) {
		 	name=object.getString("name");
			username=object.getString("username");
			password=object.getString("password");
			state=object.getString("state");
			country=object.getString("country");
			pan=object.getString("pan");
			accountNumber=object.getString("accountNumber");
			accounttype=object.getString("accounttype");
			email=object.getString("email");
			address=object.getString("address");
			contactnumber=object.getString("contactNumber");
	 }
	 
	 public void convert(Customer object) {
		 name=object.getName();
			username=object.getUsername();
			password=object.getPassword();
			state=object.getState();
			country=object.getCountry();
			pan=object.getPan();
			accountNumber=object.getAccountNumber();
			accounttype=object.getAccounttype();
			email=object.getEmail();
			address=object.getAddress();
			contactnumber=object.getContactnumber();
	 }

}
