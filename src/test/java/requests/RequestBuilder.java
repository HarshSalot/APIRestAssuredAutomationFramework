package requests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyUtils;

public class RequestBuilder {
        public static RequestSpecification req;
        public static RequestSpecification getRequestSpec(String serviceName) throws FileNotFoundException{
        	PropertyUtils config = null;
			try {
				config = new PropertyUtils("src/test/resources/global.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String baseUrl = config.getProperty(serviceName);
            String token = AuthUtils.loginAndGetToken();
            
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));
       
            req = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    .addHeader("x-ats-token", token)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-ats-app-id", "42b28d28eb9ad6a")
                    .addHeader("x-ats-language", "en")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
    }
