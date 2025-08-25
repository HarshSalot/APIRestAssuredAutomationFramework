package responses;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    /**
     * Validate response status code
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat("Status code mismatch", response.getStatusCode(), equalTo(expectedStatusCode));
    }

    /**
     * Validate response header
     */
    public static void validateHeader(Response response, String headerName, String expectedValue) {
        assertThat("Header mismatch", response.getHeader(headerName), equalToIgnoringCase(expectedValue));
    }

    /**
     * Validate response body contains field value
     */
    public static void validateField(Response response, String jsonPath, Object expectedValue) {
        assertThat("Field value mismatch", response.jsonPath().getString(jsonPath), equalTo(expectedValue));
    }

    /**
     * Validate field is not null
     */
    public static void validateFieldNotNull(Response response, String jsonPath) {
        assertThat("Field should not be null", response.jsonPath().get(jsonPath), notNullValue());
    }

    /**
     * Schema validation (optional hook with JsonUtils)
     */
    public static void validateSchema(Response response, String schemaPath) {
        // You can integrate with JsonUtils here for schema validation
        // JsonUtils.validateJsonSchema(response, schemaPath);
    }
    
    public static String extractField(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}
