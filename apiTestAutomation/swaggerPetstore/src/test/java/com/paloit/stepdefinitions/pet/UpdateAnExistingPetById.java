package com.paloit.stepdefinitions.pet;

import com.google.gson.Gson;
import com.paloit.models.pet.request.PetModelRequest;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.ShowLasResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import static com.paloit.utils.LastResponseUtils.lastResponse;
import static com.paloit.utils.PetRequest.petModelRequestObject;
import static net.serenitybdd.screenplay.ExternalValueQuestion.valueOf;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class UpdateAnExistingPetById extends SetUp {
    protected static final String RESOURCE = "/api/v3/pet";
    protected static final String DELETE_RESOURCE = "/api/v3/pet/";
    private PetModelRequest petModelRequest;

    @Before
    public void setup(){
        setUp(ACTOR_NAME);
    }

    @Given("a pet already exists in the system")
    public void aPetAlreadyExistsInTheSystem() {
        petModelRequest = petModelRequestObject();
        theActorInTheSpotlight().wasAbleTo(
                Post.to(RESOURCE).with(request -> request.headers(headers())
                        .body(new Gson().toJson(petModelRequest, PetModelRequest.class))
                        .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest()
        );
    }

    @When("the personal support change the name to {string}")
    public void thePersonalSupportChangeTheNameTo(String newName) {
        petModelRequest.setName(newName);
        theActorInTheSpotlight().attemptsTo(
                Put.to(RESOURCE).with(request -> request.headers(headers())
                        .body(new Gson().toJson(petModelRequest, PetModelRequest.class))
                        .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest()
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
                        valueOf(lastResponse().body().jsonPath().get("id")), is(petModelRequest.getId())
                ),
                seeThat(
                        "the status",
                        valueOf(lastResponse().body().jsonPath().get("status")), is(petModelRequest.getStatus())
                )
        );
    }

    @When("the personal support change the name to {string}, and someone had deleted the record before update")
    public void thePersonalSupportChangeTheNameToAndSomeoneHadDeletedTheRecordBeforeUpdate(String newName) {
        petModelRequest.setName(newName);
        theActorInTheSpotlight().attemptsTo(
                Delete.from(DELETE_RESOURCE + petModelRequest.getId()).with(request -> request.headers(headers())
                        .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest(),
                Put.to(RESOURCE).with(request -> request.headers(headers())
                        .body(new Gson().toJson(petModelRequest, PetModelRequest.class))
                        .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest()
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
}
