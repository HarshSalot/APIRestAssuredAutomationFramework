package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.util.Map;

import context.TestContext;
import executors.ApiExecutor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.ContactPayload;
import requests.APIresources;
import responses.ResponseValidator;
import utils.PayloadUtils;

public class ContactPageStepDefinition {

	private final TestContext context;
    private String updatedContactName;

	public ContactPageStepDefinition(TestContext context) {
		this.context = context;
	}

	@Given("Add contact payload with all the details")
	public void add_contact_payload_with_all_the_details(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		TestContext.contactName = "Contact"+context.getUniqueNumber();
		context.req = given().relaxedHTTPSValidation().log().all().spec(context.getRequestSpec("baseURLContact")).body(PayloadUtils.createContactPayload(TestContext.contactName, data));
	}

	@When("user in ContactPage calls {string} API with {string} method at endpoint {string}")
	public void user_in_ContactPage_calls_api_with_method_at_endpoint(String apiName, String method, String endPoint) throws Exception {
		APIresources resource = APIresources.valueOf(endPoint);
		context.rawResponse = ApiExecutor.execute(context, method, resource.getResource(), apiName);
	}
	
	@Then("the ContactPage API call got success with status code {int}")
	public void the_ContactPage_api_call_got_with_status_code(Integer expectedStatusCode) {
          ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
	}

	@And("get the ID from the response")
	public void get_the_id_from_the_response() {
		TestContext.contactId = ResponseValidator.extractField(context.rawResponse, "payload.account.contact[0].id");
		ResponseValidator.validateFieldNotNull(context.rawResponse, "payload.account.contact[0].id");
	}

	@Then("verify the contact name")
	public void verify_the_contact_name() {
		ResponseValidator.validateField(context.rawResponse, "payload.account.contact[0].firstName", TestContext.contactName);
	}

	@Given("Update contact with new details by updating name {string}")
	public void Update_contact_with_new_details_by_updating_name(String updateName) {
		updatedContactName = TestContext.contactName+"Updated";
		context.req = given().relaxedHTTPSValidation().log().all().spec(context.getRequestSpec("baseURLContact"))
				.body(PayloadUtils.buildUpdateContactPayload(updatedContactName));
	}

	@When("user in ContactPage calls {string} API with {string} method at endpoint {string} and contactId {string}")
	public void user_in_contact_page_calls_api_with_method_at_endpoint_and_contact_id(String apiName, String method, String endPoint, String contactId) throws Exception {
		APIresources resource = APIresources.valueOf(endPoint);
		contactId=TestContext.contactId;
    	context.rawResponse = ApiExecutor.execute(context, method, resource.getResource()+contactId, apiName);
	}

	@Then("verify contact is updated")
	public void get_the_response_of_Edit() {
		 ResponseValidator.validateField(context.rawResponse, "payload.account.contact[0].lastName", updatedContactName);
	}

	@Given("Delete contact setup")
	public void delete_contact_setup() {
		context.req = given().relaxedHTTPSValidation().log().all().spec(context.getRequestSpec("baseURLContact"));
	}

	@And("verify contact is deleted successfully")
	public void verify_contact_is_deleted_successfully() {
		ResponseValidator.validateField(context.rawResponse, "payload.account.contact[0].id", TestContext.contactId);
	}
}
