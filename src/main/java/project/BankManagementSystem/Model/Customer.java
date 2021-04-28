package project.BankManagementSystem.Model;



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
	 
	 
	 
	 public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String username, String password, String address, String state, String country,
			String email, String pan, String contactnumber, String accounttype, String accountNumber) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.state = state;
		this.country = country;
		this.email = email;
		this.pan = pan;
		this.contactnumber = contactnumber;
		this.accounttype = accounttype;
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
