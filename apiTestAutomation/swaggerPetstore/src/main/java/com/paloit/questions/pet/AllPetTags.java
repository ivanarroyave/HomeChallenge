package com.paloit.questions.pet;

import com.paloit.models.pet.request.PetModelResponse;
import com.paloit.questions.GetLastResponses;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.util.List;

public class AllPetTags implements Question<Boolean> {
    private final List<String> tags;

    public AllPetTags(List<String> tags) {
        this.tags = tags;
    }

    public AllPetTags areListed() {
        return this;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<PetModelResponse> petModelResponses = actor.asksFor(GetLastResponses.asType(PetModelResponse[].class));

        List<PetModelResponse> filteredPets = petModelResponses.stream()
                .filter(pet -> pet.getTags() != null && pet.getTags().stream()
                        .anyMatch(tag1 -> tags.contains(tag1.getName())))
                .toList();

        return (petModelResponses.size() == filteredPets.size()) && !petModelResponses.isEmpty();
    }

    public static AllPetTags suchAs(List<String> tags) {
        return new AllPetTags(tags);
    }
}
