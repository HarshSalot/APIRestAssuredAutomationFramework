package context;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requests.RequestBuilder;

public class TestContext {
    public Response rawResponse;
    public String responseString;
    public RequestSpecification req;
    public String token;
    public static String projectId;
    public static String specId;
    public static String contactId;
    public static String projectName;
    public static String specName;
    public static String contactName;
    private static final String uniqueNumber= String.valueOf(System.currentTimeMillis());
    public long responseTimeMs;
    private Map<String, RequestSpecification> reqSpecs = new HashMap<>();

    public RequestSpecification getRequestSpec(String serviceName){
        return reqSpecs.computeIfAbsent(serviceName, t -> {
			try {
				return RequestBuilder.getRequestSpec(t);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return req;
		});
    }  
    
    public String getUniqueNumber() {
        return uniqueNumber;
    }
}

