package requests;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.PayloadUtils;
import utils.PropertyUtils;

public class AuthUtils {

    private static String token;

    public static String loginAndGetToken() {
        if (token == null) {
            String loginEndpoint = "/login";
            
            PropertyUtils props = null;
			try {
				props = new PropertyUtils("src/test/resources/global.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            Response res = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .header("x-ats-app-id", "42b28d28eb9ad6a")
                    .header("x-ats-language", "en")
                    .body(PayloadUtils.createLoginPayload(username, password))
                    .log().all()
                    .when()
                    .post(props.getProperty("baseURLLogin")+loginEndpoint)
                    .then()
                    .log().all()
                    .extract()
                    .response();

            if (res.statusCode() != 200) {
                throw new RuntimeException("Login failed with status: " + res.statusCode());
            }

            token = res.jsonPath().getString("payload.auth.token");
            System.out.println("Retrieved Token: " + token);
        }

        return token;
    }
}
