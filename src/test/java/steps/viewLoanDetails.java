package steps;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class viewLoanDetails {
	
	private RestTemplate restTemplate;
	private String serviceUrl;
	
	private String answer;
	private ResponseEntity<String> result;
	
	@When("the customer wants to view Loan Details")
	public void the_customer_wants_to_view_loan_details() throws URISyntaxException {
		restTemplate = new RestTemplate();
		serviceUrl = "http://localhost:8080/viewloandetails";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>( headers);
		URI uri = new URI(serviceUrl);
		 
		result = restTemplate.getForEntity(uri, String.class);
		
	}
	
	@Then("customer receives gets loan details")
	public void customer_receives_gets_loan_details() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
}
