package com.saternos.tonotdo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private SeleniumController connector;

    public StepDefinitions(SeleniumController connector) {
        this.connector = connector;
    }

    @Given("^correct credentials$")
    public void correct_credentials() throws Throwable {
    }


    @When("^I load the page$")
    public void I_load_the_page() {
        connector.get("http://guest:guest@localhost:8080/");
    }

    @Then("^I should be able to log in$")
    public void I_should_see_a_greeting() {
        assertTrue(connector.contains("To NOT Do"));
    }


}
