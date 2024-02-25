package com.paloit.stepdefinitions.pet;

import com.paloit.questions.pet.AllPetStatus;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.pet.SearchPetByStatus;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class FindsPetsByStatus extends SetUp {
    @Before
    public void setup() {
        setUp(ACTOR_NAME);
    }

    @When("the employee search by {string}")
    public void theEmployeeSearchBy(String status) {
        theActorInTheSpotlight().attemptsTo(
                SearchPetByStatus.value(status)
        );
    }

    @Then("he will see a list of pets relate with the status {string}")
    public void heWillSeeListOfPetsRelate(String status) {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "all pet status should be " + status,
                        AllPetStatus.valuesAre(status)
                )
        );
    }

    @Then("he will not see any result")
    public void heWillNotSeeAnyResult() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_BAD_REQUEST,
                        response -> response.statusCode(HttpStatus.SC_BAD_REQUEST)
                ),
                ResponseConsequence.seeThatResponse(
                        "The platform will not show a list of pets",
                        response -> response.body(containsString("Input error: query parameter"))
                )
        );
    }
}
