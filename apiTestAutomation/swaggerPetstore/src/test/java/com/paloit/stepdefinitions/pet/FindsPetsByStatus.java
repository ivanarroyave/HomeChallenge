package com.paloit.stepdefinitions.pet;

import com.paloit.questions.pet.AllPetStatus;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.ShowLasResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import java.util.Collections;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class FindsPetsByStatus extends SetUp {
    protected static final String RESOURCE = "/api/v3/pet/findByStatus";

    @Before
    public void setup() {
        setUp(ACTOR_NAME);
    }

    @When("the pet store employee search by {string}")
    public void thePetStoreEmployeeSearchBy(String statusValue) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Get.resource(RESOURCE).with(request -> request.headers(headers())
                                .params(Collections.singletonMap("status", statusValue))
                                .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest()
        );
    }

    @Then("he will see a list of pets relate with the status {string}")
    public void heWillSeeListOfPetsRelate(String status) {
        OnStage.theActorInTheSpotlight().should(
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
        OnStage.theActorInTheSpotlight().should(
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
