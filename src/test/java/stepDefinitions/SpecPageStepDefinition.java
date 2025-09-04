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

public class SpecPageStepDefinition {

    private final TestContext context;
    private String updatedSpecName;

    public SpecPageStepDefinition(TestContext context) {
        this.context = context;
    }

    @Given("Create spec payload")
    public void create_spec_payload() {
    	  TestContext.specName = "Spec"+context.getUniqueNumber();
        context.req = given()
        		.log().all()
        		.spec(context.getRequestSpec("baseURLSpec"))
                .body(PayloadUtils.buildCreateSpecPayload(TestContext.specName));
    }

    @When("user in SpecPage calls {string} API with {string} method at endpoint {string}")
    public void user_in_spec_page_calls_api_with_method_at_endpoint(String apiName, String method, String endPoint) throws Exception {
    	APIresources resource = APIresources.valueOf(endPoint);
    	context.rawResponse = ApiExecutor.execute(context, method, resource.getResource(), apiName);
	}
    
    @Then("the SpecPage API call success with status code {int}")
    public void the_spec_page_api_call_success_with_status_code(Integer expectedStatusCode) {
        // Write code here that turns the phrase above into concrete actions
    	ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
    }
    
    @Then("response body contains spec id")
    public void response_body_contains_spec_id() {
        TestContext.specId = context.rawResponse.jsonPath().getString("payload.spec.spec[0].id");
        ResponseValidator.validateFieldNotNull(context.rawResponse, "payload.spec.spec[0].id");
    }
    
    @Then("verify the spec name")
    public void verify_the_spec_name() {
		ResponseValidator.validateField(context.rawResponse, "payload.spec.spec[0].name", TestContext.specName);
    }

    @Given("spec id with the payload update")
    public void spec_id_with_the_payload_update() {
    	updatedSpecName = "Spec"+"Updated"+context.getUniqueNumber();
        context.req = given()
        		.relaxedHTTPSValidation()
        		.log().all()
        		.spec(context.getRequestSpec("baseURLSpec"))
                .body(PayloadUtils.buildCreateSpecPayload(updatedSpecName));
    }

    @When("user in SpecPage calls {string} API with {string} method at endpoint {string} and specId {string}")
    public void user_in_spec_page_calls_api_with_method_at_endpoint_and_spec_id(String apiName, String method, String endPoint, String specId) throws Exception {
    	APIresources resource = APIresources.valueOf(endPoint);
    	specId=TestContext.specId;
    	context.rawResponse = ApiExecutor.execute(context, method, resource.getResource()+specId, apiName);
    }

    @Then("verify the updated spec name")
    public void verify_the_updated_spec_name() {
        ResponseValidator.validateField(context.rawResponse, "payload.spec.spec[0].name", updatedSpecName);
    }
    
    @Given("spec id with the payload")
    public void spec_id_with_the_payload() {
        context.req = given()
        		.relaxedHTTPSValidation()
        		.log().all()
        		.spec(context.getRequestSpec("baseURLSpec"));
    }
    
    @Then("verify the deleted spec")
    public void verify_the_deleted_spec() {
        ResponseValidator.validateField(context.rawResponse, "payload.spec.spec[0].id", TestContext.specId);
    }
}
