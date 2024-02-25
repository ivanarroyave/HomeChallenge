package com.paloit.stepdefinitions.pet;

import com.paloit.models.pet.request.PetModelRequest;
import com.paloit.questions.GetLastResponse;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.pet.AddNewPet;
import com.paloit.tasks.pet.DeleteThePet;
import com.paloit.tasks.pet.UpdateAnExistingPet;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import java.util.UUID;

import static com.paloit.utils.LastResponseUtils.lastResponse;
import static net.serenitybdd.screenplay.ExternalValueQuestion.valueOf;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class UpdateAnExistingPetById extends SetUp {
    private PetModelRequest petModelRequest;

    @Before
    public void setup(){
        setUp(ACTOR_NAME);
    }

    @Given("a pet already exists in the system")
    public void aPetAlreadyExistsInTheSystem() {
        theActorInTheSpotlight().wasAbleTo(
                AddNewPet.ToTheStore()
        );
    }

    @When("the personal support change the name to {string}")
    public void thePersonalSupportChangeTheNameTo(String newName) {
        petModelRequest = theActorInTheSpotlight().asksFor(GetLastResponse.asType(PetModelRequest.class));
        petModelRequest.setName(newName);

        theActorInTheSpotlight().attemptsTo(
                UpdateAnExistingPet.withTheNewInformation(petModelRequest)
        );
    }

    @Then("the platform will taken the change")
    public void thePlatformWillTakenTheChange() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "the name",
                        valueOf(lastResponse().body().jsonPath().get("name")), is(petModelRequest.getName())
                ),
                seeThat(
                        "the id",
                        valueOf(lastResponse().body().jsonPath().get("id")), is(Integer.valueOf(petModelRequest.getId()))
                ),
                seeThat(
                        "the status",
                        valueOf(lastResponse().body().jsonPath().get("status")), is(petModelRequest.getStatus())
                )
        );
    }

    @When("the personal support change the name to {string}, and someone had deleted the record before update")
    public void thePersonalSupportChangeTheNameToAndSomeoneHadDeletedTheRecordBeforeUpdate(String newName) {
        petModelRequest = theActorInTheSpotlight().asksFor(GetLastResponse.asType(PetModelRequest.class));
        petModelRequest.setName(newName);

        theActorInTheSpotlight().attemptsTo(
                DeleteThePet.withId(petModelRequest.getId()),
                UpdateAnExistingPet.withTheNewInformation(petModelRequest)
        );
    }

    @Then("the platform will indicate an alert related to pet not found")
    public void thePlatformWillIndicateAnAlertRelatedToPetNotFound() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_NOT_FOUND,
                        response -> response.statusCode(HttpStatus.SC_NOT_FOUND)
                ),
                ResponseConsequence.seeThatResponse(
                        "The platform will not update the pet information because the pet was not found",
                        response -> response.body(containsString("Pet not found"))
                )
        );
    }

    @When("the personal support change the name to {string}, and some invalid format id is captured by the system")
    public void thePersonalSupportChangeTheNameToAndSomeInvalidFormatIdIsCapturedByTheSystem(String newName) {
        petModelRequest = theActorInTheSpotlight().asksFor(GetLastResponse.asType(PetModelRequest.class));
        petModelRequest.setId(UUID.randomUUID().toString());
        petModelRequest.setName(newName);

        theActorInTheSpotlight().attemptsTo(
                DeleteThePet.withId(petModelRequest.getId()),
                UpdateAnExistingPet.withTheNewInformation(petModelRequest)
        );
    }

    @Then("the platform will indicate an alert related to the invalid id supplied")
    public void thePlatformWillIndicateAnAlertRelatedToTheInvalidIdSupplied() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_BAD_REQUEST,
                        response -> response.statusCode(HttpStatus.SC_BAD_REQUEST)
                ),
                seeThat(
                        "the Invalid ID supplied message",
                        valueOf(lastResponse().body().jsonPath().get("message")),
                        is("Input error: unable to convert input to io.swagger.petstore.model.Pet")
                )
        );
    }
}
