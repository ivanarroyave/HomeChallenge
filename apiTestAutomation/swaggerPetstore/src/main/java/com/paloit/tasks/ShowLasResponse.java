package com.paloit.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ShowLasResponse implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {
        LastResponse.received().answeredBy(t).getBody().prettyPrint();
    }

    public static ShowLasResponse ofRequest() {
        return new ShowLasResponse();
    }
}
