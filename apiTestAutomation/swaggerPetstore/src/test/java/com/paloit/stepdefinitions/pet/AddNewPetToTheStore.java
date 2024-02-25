package com.paloit.stepdefinitions.pet;

import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.pet.AddNewPet;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import static com.paloit.utils.LastResponseUtils.lastResponse;
import static net.serenitybdd.screenplay.ExternalValueQuestion.valueOf;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.is;

public class AddNewPetToTheStore extends SetUp {

    @Before
    public void setup() {
        setUp(ACTOR_NAME);
    }

    @When("the pet is enrolled")
    public void thePetIsEnrolled() {
        theActorInTheSpotlight().attemptsTo(
                AddNewPet.ToTheStore()
        );
    }

    @Then("the pet will be available in the platform")
    public void thePetWillBeAvailableInThePlatform() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "the enrolled pet status",
                        valueOf(lastResponse().body().jsonPath().get("status")),
                        is("available")
                )
        );
    }
}
