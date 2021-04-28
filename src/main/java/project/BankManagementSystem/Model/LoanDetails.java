package project.BankManagementSystem.Model;


public class LoanDetails {
	
	private String loanType;
	private String loanAmount;
	private String date;
	private String rateOfInterest;
	private String durationOfLoan;
	
	
	public LoanDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanDetails(String loanType, String loanAmount, String date, String rateOfInterest, String durationOfLoan) {
		super();
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.date = date;
		this.rateOfInterest = rateOfInterest;
		this.durationOfLoan = durationOfLoan;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getDurationOfLoan() {
		return durationOfLoan;
	}

	public void setDurationOfLoan(String durationOfLoan) {
		this.durationOfLoan = durationOfLoan;
	}

	 
	 public void convert(LoanDetails object) {
		 	loanType=object.getLoanType();
		 	loanAmount=object.getLoanAmount();
		 	date=object.getDate();
		 	rateOfInterest=object.getRateOfInterest();
		 	durationOfLoan=object.getDurationOfLoan();
	 }
}
