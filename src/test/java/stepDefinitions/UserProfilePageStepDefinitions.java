package stepDefinitions;

import static org.junit.Assert.*;

import context.TestContext;
import executors.ApiExecutor;
import io.cucumber.java.en.*;
import responses.ResponseValidator;

public class UserProfilePageStepDefinitions {

	private final TestContext context;

	public UserProfilePageStepDefinitions(TestContext context) {
		this.context = context;
	}

	@Given("user is authenticated with valid token")
	public void user_is_authenticated_with_valid_token() {
		// Assuming token is already set in TestContext from Login
		assertNotNull("Token should not be null", context.token);
	}

	@When("user with id {string} in UserProfilePage calls {string} API with {string} method at endpoint {string}")
	public void user_with_id_in_user_profile_page_calls_api_with_method_at_endpoint(String userId, String apiName, String method, String endPoint) throws Exception {
		context.rawResponse = ApiExecutor.execute(context, method, endPoint+userId, apiName);
//	    context.responseString = context.rawResponse.then().log().all().extract().asString();
	}

	@Then("the UserProfilePage API response status code should be {int}")
	public void the_user_profile_page_api_response_status_code_should_be(Integer expectedStatusCode) {
          ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
	}

	@Then("response body should contain {string} as {string}")
	public void response_body_should_contain_field_as_value(String field, String expectedValue) {
		switch (field) {
		case "firstName":
			ResponseValidator.validateField(context.rawResponse, "payload.account.user[0].firstName", expectedValue);
			break;
		case "lastName":
			ResponseValidator.validateField(context.rawResponse, "payload.account.user[0].lastName", expectedValue);
			break;
		case "address1":
			ResponseValidator.validateField(context.rawResponse, "payload.account.user[0].office.address1", expectedValue);
			break;
		case "city":
			ResponseValidator.validateField(context.rawResponse, "payload.account.user[0].office.location.city.value", expectedValue);
			break;
		case "companyName":
			ResponseValidator.validateField(context.rawResponse, "payload.account.user[0].firm.companyName", expectedValue);
			break;
		default:
			fail("Field " + field + " is not supported");
		}
	}
}
