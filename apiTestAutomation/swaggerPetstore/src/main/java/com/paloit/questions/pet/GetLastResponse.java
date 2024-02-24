package com.paloit.questions.pet;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetLastResponse<T> implements Question<T> {
    private final Class<T> responseType;

    public GetLastResponse(Class<T> responseType) {
        this.responseType = responseType;
    }

    @Override
    public T answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(responseType);
    }

    public static <T> GetLastResponse<T> asType(Class<T> responseType) {
        return new GetLastResponse<>(responseType);
    }
}
