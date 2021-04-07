package project.BankManagementSystem.Model;

import java.util.Date;

import org.jnosql.artemis.Entity;

import com.couchbase.client.java.document.json.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LoanDetails {
	
	private String loanType;
	private String loanAmount;
	private String date;
	private String rateOfInterest;
	private String durationOfLoan;
	
	
	public JsonObject toJson() {
		 JsonObject json=JsonObject.empty()
					.put("loanType", loanType)
					.put("loanAmount", loanAmount)
					.put("date", date)
					.put("rateOfInterest", rateOfInterest)
					.put("durationOfLoan",durationOfLoan);
		 return json;
		 
	 }
	 
	 public void fromJson(JsonObject object) {
		 loanType=object.getString("loanType");
		 loanAmount=object.getString("loanAmount");
		 date=object.getString("date");
		 rateOfInterest=object.getString("rateOfInterest");
		 durationOfLoan=object.getString("durationOfLoan");
	 }
}
