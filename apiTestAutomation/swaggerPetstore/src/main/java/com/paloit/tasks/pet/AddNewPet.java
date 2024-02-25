package com.paloit.tasks.pet;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.Json.generateJson;
import static com.paloit.utils.PetRequest.petModelRequestObject;
import static com.paloit.utils.pet.PetResources.ADD_NEW_PET_TO_THE_STORE;

public class AddNewPet implements Task {

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Post.to(ADD_NEW_PET_TO_THE_STORE.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                                .body(generateJson(petModelRequestObject()))
                        )
        );
    }

    public static AddNewPet ToTheStore() {
        return new AddNewPet();
    }
}
