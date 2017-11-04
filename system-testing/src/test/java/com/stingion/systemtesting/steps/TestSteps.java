package com.stingion.systemtesting.steps;

import com.stingion.systemtesting.ApplicationService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestSteps {

    @Autowired
    private ApplicationService appService;
    private int result;

    @Given("a variable x with value $value")
    public void givenXValue(@Named("value") int value) {
        appService.setX(value);
    }

    @When("I multiply x by $value")
    public void whenImultiplyXBy(@Named("value") int value) {
        result = appService.multiply(appService.getX(), value);
    }

    @Then("result should equal $value")
    public void thenXshouldBe(@Named("value") int value) {
        if (value != result)
            throw new RuntimeException("result is " + result + ", but should be " + value);
    }
}