package com.paloit.tasks.pet;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.pet.PetResources.DELETE_RESOURCE;

public class DeleteThePet implements Task {
    private final String id;

    public DeleteThePet(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Delete.from(DELETE_RESOURCE.getValue() + id)
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                        )
        );
    }

    public static DeleteThePet withId(String id) {
        return new DeleteThePet(id);
    }
}
