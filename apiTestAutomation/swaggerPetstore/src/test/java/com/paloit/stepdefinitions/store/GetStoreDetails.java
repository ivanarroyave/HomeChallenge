package com.paloit.stepdefinitions.store;

import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.store.GetAllTheInventory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;

import static com.paloit.utils.LastResponseUtils.lastResponse;
import static net.serenitybdd.screenplay.ExternalValueQuestion.valueOf;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetStoreDetails extends SetUp {

    @Before
    public void setup() {
        setUp(ACTOR_NAME);
    }

    @When("the employee does a general search")
    public void theEmployeeDoesAGeneralSearch() {
        theActorInTheSpotlight().attemptsTo(
                GetAllTheInventory.untilNow()
        );
    }

    @Then("he will see all totals are groped")
    public void heWillSeeAllTotalsGropedByStatus() {
        theActorInTheSpotlight().should(
                seeThatResponse(
                        HTTP_STATUS_CODE_MESSAGE + HttpStatus.SC_OK,
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "approved status",
                        valueOf(lastResponse().body().jsonPath().get("approved")), CoreMatchers.notNullValue()
                ),
                seeThat(
                        "placed status",
                        valueOf(lastResponse().body().jsonPath().get("placed")), CoreMatchers.notNullValue()
                ),
                seeThat(
                        "delivered status",
                        valueOf(lastResponse().body().jsonPath().get("delivered")), CoreMatchers.notNullValue()
                )
        );
    }
}
