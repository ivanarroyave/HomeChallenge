package com.paloit.tasks.pet;

import com.paloit.models.pet.request.PetModelRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.Json.generateJson;
import static com.paloit.utils.pet.PetResources.UPDATE_RESOURCE;

public class UpdateAnExistingPet implements Task {
    private final PetModelRequest petModelRequest;

    public UpdateAnExistingPet(PetModelRequest petModelRequest) {
        this.petModelRequest = petModelRequest;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Put.to(UPDATE_RESOURCE.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                                .body(generateJson(petModelRequest))
                        )
        );
    }

    public static UpdateAnExistingPet withTheNewInformation(PetModelRequest petModelRequest) {
        return new UpdateAnExistingPet(petModelRequest);
    }
}
