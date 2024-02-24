package com.paloit.questions.pet;

import com.paloit.models.pet.request.PetModelResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class AllPetStatus implements Question<Boolean> {
    private final String value;

    public AllPetStatus(String value) {
        this.value = value;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        PetModelResponse[] petModelResponses = LastResponse.received().answeredBy(OnStage.theActorInTheSpotlight())
                .then().extract().response().as(PetModelResponse[].class);

        for (PetModelResponse petModelResponse : petModelResponses)
            if (!petModelResponse.getStatus().equals(value))
               return false;

        return true;
    }

    public static AllPetStatus valuesAre(String value) {
        return new AllPetStatus(value);
    }
}
