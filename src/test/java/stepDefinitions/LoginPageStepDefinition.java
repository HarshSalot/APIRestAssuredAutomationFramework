package stepDefinitions;

import static io.restassured.RestAssured.given;

import context.TestContext;
import executors.ApiExecutor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import requests.APIresources;
import responses.ResponseValidator;
import utils.PayloadUtils;

public class LoginPageStepDefinition {
	
	private final TestContext context;
	 public LoginPageStepDefinition(TestContext context) {
	        this.context = context;
	    }

	@Given("Add Login Payload with {string} {string}")
	public void add_login_payload_with(String username, String password) {		
		context.req= given()
                .relaxedHTTPSValidation()
                .log().all()
                .spec(context.getRequestSpec("baseURLLogin"))
                .body(PayloadUtils.createLoginPayload(username, password));
	}
	
	@When("user in LoginPage calls {string} API with {string} method at endpoint {string}")
	public void user_in_LoginPage_calls_api_with_method_at_endpoint(String apiName, String method, String endPoint) throws Exception {
		APIresources resource = APIresources.valueOf(endPoint);
		context.rawResponse = ApiExecutor.execute(context, method, resource.getResource(), apiName);
	}
	
	@Then("the API call got {string} with status code {int}")
	public void the_api_call_got_with_status_code(String result,Integer expectedStatusCode) {
		ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
	}
	
	@Then("the response body contains {string} with value {string}")
	public void then_the_response_body_contains_with_value(String string, String string2) {
		ResponseValidator.extractField(context.rawResponse, "payload.error");
	}

	@Given("Add forgot Payload with {string}")
	public void add_forgot_payload_with(String email) {
	    // Write code here that turns the phrase above into concrete actions		
		context.req =given()
                .relaxedHTTPSValidation()
                .log().all()
                .spec(context.getRequestSpec("baseURLLogin"))
                .body(PayloadUtils.buildForgetPayload(email));
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
		ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
	}

	@Given("Add signup payload with all the details")
	public void add_signup_payload_with_all_the_details() {
	    // Write code here that turns the phrase above into concrete actions	
		String email = "Testuser_" + context.getUniqueNumber() + "@gmail.com";
		context.req =given().relaxedHTTPSValidation().log().all().spec(context.getRequestSpec("baseURLLogin")).body(PayloadUtils.buildSignUpPayload(email));
	}
	
	@Then("get the {string}{string}{string} from the response")
	public void get_the_from_the_response(String userID, String firmID, String officeID) {
		userID =ResponseValidator.extractField(context.rawResponse, "payload.auth.user[0].id");
		firmID =ResponseValidator.extractField(context.rawResponse, "payload.auth.user[0].firmId");
		officeID =ResponseValidator.extractField(context.rawResponse, "payload.auth.user[0].officeId");
		System.out.println("UserId:" + userID + "FirmID:" + firmID + "OfficeID:" +officeID);
	}

}
