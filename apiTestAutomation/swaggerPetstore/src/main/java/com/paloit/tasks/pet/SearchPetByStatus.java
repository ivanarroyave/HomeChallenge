package com.paloit.tasks.pet;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.pet.PetResources.FIND_BY_STATUS;

public class SearchPetByStatus implements Task {
    private static final String STATUS = "status";
    private final String status;

    public SearchPetByStatus(String status) {
        this.status = status;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Get.resource(FIND_BY_STATUS.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                                .params(Collections.singletonMap(STATUS, status))
                        )
        );
    }

    public static SearchPetByStatus value(String status) {
        return new SearchPetByStatus(status);
    }
}
