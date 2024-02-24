package com.paloit.stepdefinitions.pet;

import com.google.gson.Gson;
import com.paloit.models.pet.request.PetModelRequest;
import com.paloit.tasks.ShowLasResponse;
import com.paloit.stepdefinitions.configuration.SetUp;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;

import static com.paloit.utils.PetRequest.petModelRequestObject;
import static net.serenitybdd.screenplay.ExternalValueQuestion.valueOf;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.is;

public class AddNewPetToTheStore extends SetUp {
    protected static final String RESOURCE = "/api/v3/pet";
    private PetModelRequest petModelRequest;

    @Before
    public void setup(){
        setUp(ACTOR_NAME);
    }

    @Given("there is a cute pet in the reception")
    public void thereIsACutePetInTheReception() {
        petModelRequest = petModelRequestObject();
    }

    @When("the pet is enrolled")
    public void thePetIsEnrolled() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Post.to(RESOURCE).with(request -> request.headers(headers())
                                .body(new Gson().toJson(petModelRequest, PetModelRequest.class))
                                .relaxedHTTPSValidation()),
                ShowLasResponse.ofRequest()
        );
    }

    @Then("the pet will be available in the platform")
    public void thePetWillBeAvailableInThePlatform() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "the enrolled pet status",
                        valueOf(LastResponse.received().answeredBy(OnStage.theActorInTheSpotlight()).body().jsonPath().get("status")),
                        is("available")
                )
        );
    }
}
