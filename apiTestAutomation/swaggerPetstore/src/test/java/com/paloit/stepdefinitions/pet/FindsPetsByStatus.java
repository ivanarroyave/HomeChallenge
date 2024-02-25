package com.paloit.stepdefinitions.pet;

import com.paloit.exceptions.DoNotMatch;
import com.paloit.questions.pet.AllPetStatus;
import com.paloit.questions.pet.AllPetTags;
import com.paloit.stepdefinitions.configuration.SetUp;
import com.paloit.tasks.pet.AddNewPet;
import com.paloit.tasks.pet.FindsPetsByTags;
import com.paloit.tasks.pet.SearchPetByStatus;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.paloit.exceptions.DoNotMatch.VALIDATION_DO_NOT_MATCH;
import static com.paloit.utils.PetRequestBody.createPetModelRequest;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class FindsPetsByStatus extends SetUp {
    private List<String> tags = new ArrayList<>();
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

    @Given("exists a pet with the {string}")
    public void existsAPetWithThe(String tag) {
        theActorInTheSpotlight().wasAbleTo(
                AddNewPet.ToTheStore().withTheNextSpecification(createPetModelRequest(tag))
        );
    }

    @When("the employee search by tag {string}")
    public void theEmployeeSearchByTag(String tag) {
        theActorInTheSpotlight().attemptsTo(
                FindsPetsByTags.suchAs(Collections.singletonList(tag))
        );
    }

    @Then("he will see a list of pets related with the tag {string}")
    public void heWillSeeAListOfPetsRelatedWithTheTag(String tag) {
        theActorInTheSpotlight().should(
                seeThat(
                        "all results are related with the tag " + tag,
                        AllPetTags.as(tag).areListed(),
                        is(true)
                )
        );
    }

    @Given("exists some pets with this tags")
    public void existsSomePetsWithThisTags(List<String> tags) {
        this.tags.addAll(tags);
        for (String tag : tags) {
            theActorInTheSpotlight().wasAbleTo(
                    AddNewPet.ToTheStore().withTheNextSpecification(createPetModelRequest(tag))
            );
        }
    }

    @Given("we know that tag {string} doesn't exist")
    public void weKnowThatTagDoesNotExist(String tag) {
        this.tags.add(tag);

        theActorInTheSpotlight().attemptsTo(
                FindsPetsByTags.suchAs(Collections.singletonList(tag))
        );

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(AllPetTags.as(tag).areListed()).isFalse()
                        .orElseThrow(
                                new DoNotMatch(String.format(VALIDATION_DO_NOT_MATCH, "The tag \"" + tag + "\" already exists."))
                        )
        );
    }

    @When("the employee search by all mentioned tags before")
    public void theEmployeeSearchByAllMentionedTags() {
        theActorInTheSpotlight().attemptsTo(
                FindsPetsByTags.suchAs(tags)
        );
    }

    @Then("he will see a list of pets related only with existing tags")
    public void heWillSeeListOfPetsRelatedOnlyWithExistingTags() {

    }
}
