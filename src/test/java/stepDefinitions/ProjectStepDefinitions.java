package stepDefinitions;

import static io.restassured.RestAssured.given;
import responses.ResponseValidator;
import io.cucumber.java.en.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import context.TestContext;
import executors.ApiExecutor;

public class ProjectStepDefinitions {

	private final TestContext context;
    String basePath;

    public ProjectStepDefinitions(TestContext context) {
        this.context = context;
    }
    
    @Given("User is authenticated with valid token")
    public void user_is_authenticated_with_valid_token() {
    }

    @Given("Create project payload from {string}")
    public void create_project_payload_from_file(String fileName) throws IOException {
        String payloadPath = "src/test/resources/payloads/" + fileName;
        String jsonBody = new String(Files.readAllBytes(Paths.get(payloadPath)));
        TestContext.projectName = "Project"+context.getUniqueNumber();
        String finalPayload = jsonBody.replace("{{name}}",  TestContext.projectName);

        context.req = given()
        	.log().all()
        	.spec(context.getRequestSpec("baseURLProject"))
            .body(finalPayload);
    }

    @When("user calls {string} API with {string} method at endpoint {string}")
    public void user_calls_api_with_method(String apiName, String method, String endPoint) throws Exception {
    	context.rawResponse = ApiExecutor.execute(context, method, endPoint, apiName);
    }  

    @Then("the ProjectPage API call is successful with status code {int}")
    public void the_ProjectPage_api_call_successful_with_status_code(Integer expectedStatusCode) {
        ResponseValidator.validateStatusCode(context.rawResponse, expectedStatusCode);
    }

    @And("response contains a valid project id")
    public void response_contains_valid_project_id() {
    	TestContext.projectId =ResponseValidator.extractField(context.rawResponse, "payload.project.project[0].id");
    	ResponseValidator.validateFieldNotNull(context.rawResponse, "payload.project.project[0].id");
        System.out.println("Created Project ID: " + TestContext.projectId);
    }
  
    @Then("verify project name from the response")
    public void verify_project_name_from_the_response() {
        ResponseValidator.validateField(context.rawResponse, "payload.project.project[0].name", TestContext.projectName);
    }
  
    @Given("a valid project ID to delete")
    public void a_valid_project_id_to_delete() {
    }
    
    @When("user calls {string} API with {string} method at endpoint {string} and projectId {string}")
    public void user_calls_api_with_method_at_endpoint_and_project_id(String apiName, String method, String endPoint, String projectId) throws Exception {
    	projectId=TestContext.projectId;
    	context.rawResponse = ApiExecutor.execute(context, method, endPoint+projectId, apiName);
    }

    @Then("verify project is deleted successfully")
    public void verify_project_is_deleted_successfully() {
         ResponseValidator.validateField(context.rawResponse, "payload.project.project[0].id", TestContext.projectId);
    }
}

