package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.ContactPayload;

import java.util.HashMap;
import java.util.Map;

public class PayloadUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Build user payload dynamically using Map
     */
    public static Map<String, Object> createUserPayload(String name, String email, String role) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("email", email);
        payload.put("role", role);
        return payload;
    }
    
    public static ContactPayload createContactPayload(String contactName, Map<String, String> data) {
    	// Serialization from JavaObject to Json using pojo class
    	ContactPayload payload = new ContactPayload();
        payload.setFirstName(contactName);
        payload.setOwnerId(Integer.parseInt(data.get("ownerId")));
        payload.setOwnerType(data.get("ownerType"));
        payload.setSetAsDefaultEmail(Boolean.parseBoolean(data.get("setAsDefaultEmail")));
        return payload;
    }

    /**
     * Generic payload builder from Map
     */
    public static Map<String, Object> buildPayload(Map<String, Object> input) {
        return new HashMap<>(input);
    }

    /**
     * Convert POJO to JSON String
     */
    public static String convertPojoToJson(Object pojo) {
        try {
            return mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting POJO to JSON", e);
        }
    }

    /**
     * Convert JSON String to POJO
     */
    public static <T> T convertJsonToPojo(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to POJO", e);
        }
    }
    
    /**
     * Build login payload using Map
     */
    public static Map<String, String> createLoginPayload(String username, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);
        return payload;
    }
    
    /**
     * Loginpage Forget pass Json String payload
     */
    public static String buildForgetPayload(String email) {
        return "{ \"email\": \"" + email + "\" }";
    }
    
    /**
     * Loginpage SignUp Json String payload
     */
    public static String buildSignUpPayload(String email) {
    	String signUpJsonStringPayload =  "{\r\n"
    			+ "    \"lastName\": \"Mali\",\r\n"
    			+ "    \"companyName\": \"Enterprise\",\r\n"
    			+ "    \"firmTypeId\": 4,\r\n"
    			+ "    \"location\": {\r\n"
    			+ "        \"country\": {\r\n"
    			+ "            \"id\": \"6251999\",\r\n"
    			+ "            \"value\": \"Canada\"\r\n"
    			+ "        },\r\n"
    			+ "        \"province\": {\r\n"
    			+ "            \"id\": \"6093943\",\r\n"
    			+ "            \"value\": \"Ontario\"\r\n"
    			+ "        },\r\n"
    			+ "        \"city\": {\r\n"
    			+ "            \"id\": \"6167865\",\r\n"
    			+ "            \"value\": \"Toronto\"\r\n"
    			+ "        }\r\n"
    			+ "    },\r\n"
    			+ "    \"postalZip\": \"M3H4Y2\",\r\n"
    			+ "    \"jobTitle\": \"Owner/Partner\",\r\n"
    			+ "    \"firstName\": \"Sali\",\r\n"
    			+ "    \"confirmPassword\": \"Toronto@123\",\r\n"
    			+ "    \"password\": \"Toronto@123\",\r\n"
    			+ "    \"email\": \""+email+"\",\r\n"
    			+ "    \"address1\": \"325 Milner Ave\"\r\n"
    			+ "}";
        return signUpJsonStringPayload;
    }
    
    /**
     * SpecPage create spec Json String payload
     */
    public static String buildCreateSpecPayload(String specName) {
        return "{\"name\":\""+specName+"\"}";
    }
    
    /**
     * SpecPage update spec Json String payload
     */
    public static String buildUpdateSpecPayload(String updatedSpecName) {
        return "{\"name\":\""+updatedSpecName+"\"}";
    }
    
    /**
     * ContactPage update contact Json String payload
     */
    public static String buildUpdateContactPayload(String updatedContactName) {
        return "{\"lastName\":\""+updatedContactName+"\"}";
    }
}
