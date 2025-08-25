package stepDefinitions;

import context.TestContext;
import io.cucumber.java.Before;
import requests.AuthUtils;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 0)
    public void beforeScenario() throws Exception {
    	context.token = AuthUtils.loginAndGetToken();

        // preload all services if needed
        context.getRequestSpec("baseURLLogin");
        context.getRequestSpec("baseURLContact");
        context.getRequestSpec("baseURLSpec");
        context.getRequestSpec("baseURLProject");

        System.out.println("Request specifications initialized in Hooks.");
        }
    }
