package com.paloit.utils;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LastResponseUtils {
    public static Response lastResponse() {
        return LastResponse.received().answeredBy(theActorInTheSpotlight());
    }
}
