package com.paloit.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;
import java.util.List;

public class GetLastResponses<T> implements Question<List<T>> {
    private final Class<T[]> responseTypeArray;

    public GetLastResponses(Class<T[]> responseTypeArray) {
        this.responseTypeArray = responseTypeArray;
    }

    @Override
    public List<T> answeredBy(Actor actor) {
        return List.of(SerenityRest.lastResponse().as(responseTypeArray));
    }

    public static <T> GetLastResponses<T> asType(Class<T[]> responseTypeArray) {
        return new GetLastResponses<>(responseTypeArray);
    }
}
