package executors;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import context.TestContext;

public class ApiExecutor {
	
    public static long responseTimeMs;

    public static Response execute(TestContext context, String method, String endPoint, String apiName) throws Exception {
    	// Initialize only if not already set in a Given step
        if (context.req == null) {
            context.req = given()
                    .spec(context.getRequestSpec(resolveService(apiName)));
        }

        switch (method.toUpperCase()) {
            case "POST":
            	context.rawResponse = context.req.when().post(endPoint);
    			if (apiName.equals("contact")) {
    				responseTimeMs = context.rawResponse.getTime(); // response time in ms
    				logResponseDetails(context.rawResponse, context);
    			}
                return context.rawResponse;
            case "PATCH":
                return context.req.when().patch(endPoint);
            case "PUT":
                return context.req.when().put(endPoint);
            case "DELETE":
                return context.req.when().delete(endPoint);
            case "GET":
                return context.req.when().get(endPoint);
            default:
                throw new IllegalArgumentException("Unsupported method: " + method);
        }
    }

    private static String resolveService(String apiName) {
        switch (apiName.toLowerCase()) {
            case "project": return "baseURLProject";
            case "contact": return "baseURLContact";
            case "spec": return "baseURLSpec";
            case "auth": return "baseURLAuth";
            default: return "baseURLProject"; // fallback
        }
    }

    private static String getResourceId(String apiName, TestContext context) {
        switch (apiName.toLowerCase()) {
            case "project": return context.projectId;
            case "spec": return context.specId;
            case "contact": return context.contactId;
            default: return "";
        }
    }
    
	// Helper method: Logs headers + response time and optionally validates
	private static void logResponseDetails(Response response, TestContext context) {
		System.out.println("Response Time: " + context.responseTimeMs + " ms");
		if (context.responseTimeMs > 2000) {
			System.out.println("⚠️ Warning: API response time exceeded 2 seconds");
		}

		String contentType = response.getHeader("Content-Type");
		assertNotNull("Content-Type header is missing", contentType);
		assertTrue("Expected application/json content type", contentType.contains("application/json"));

		System.out.println("Headers:");
		response.getHeaders().forEach(header -> System.out.println("  " + header.getName() + ": " + header.getValue()));
	}
}
