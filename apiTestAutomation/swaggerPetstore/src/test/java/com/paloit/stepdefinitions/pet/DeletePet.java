package com.paloit.stepdefinitions.pet;

import com.paloit.models.pet.request.PetModelResponse;
import com.paloit.questions.GetLastResponse;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.pet.DeleteThePet;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class DeletePet extends SetUp {
    @Before
    public void setup() {
        setUp(ACTOR_NAME);
    }

    @When("the employee execute the delete process")
    public void theEmployeeExecuteTheDeleteProcess() {
        int petId = theActorInTheSpotlight().asksFor(GetLastResponse.asType(PetModelResponse.class)).getId();

        theActorInTheSpotlight().attemptsTo(
                DeleteThePet.withId(String.valueOf(petId))
        );
    }

    @Then("the platform will delete the pet from the system")
    public void thePlatformWillDeleteThePetFromTheSystem() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                ResponseConsequence.seeThatResponse(
                        "The platform has deleted the pet",
                        response -> response.body(containsString("Pet deleted"))
                )
        );
    }
}
