package com.paloit.questions.pet;

import com.paloit.models.pet.request.PetModelResponse;
import com.paloit.questions.GetLastResponses;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class AllPetTags implements Question<Boolean> {
    private final String tag;

    public AllPetTags(String tag) {
        this.tag = tag;
    }

    public AllPetTags areListed() {
        return this;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<PetModelResponse> petModelResponses = actor.asksFor(GetLastResponses.asType(PetModelResponse[].class));

        List<PetModelResponse> filteredPets = petModelResponses.stream()
                .filter(pet -> pet.getTags() != null && pet.getTags().stream()
                        .anyMatch(tag1 -> tag.equals(tag1.getName())))
                .toList();

        return (petModelResponses.size() == filteredPets.size()) && !petModelResponses.isEmpty();
    }

    public static AllPetTags as(String tag) {
        return new AllPetTags(tag);
    }
}
