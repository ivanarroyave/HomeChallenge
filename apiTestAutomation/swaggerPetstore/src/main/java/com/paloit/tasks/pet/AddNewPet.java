package com.paloit.tasks.pet;

import com.paloit.models.pet.request.PetModelRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.Json.generateJson;
import static com.paloit.utils.PetRequestBody.defaultPetModelRequestObject;
import static com.paloit.utils.apiresources.PetResources.ADD_NEW_PET_TO_THE_STORE;

public class AddNewPet implements Task {
    private PetModelRequest petModelRequest;
    private boolean isCustomRequestMode;

    public AddNewPet withTheNextSpecification(PetModelRequest petModelRequest) {
        this.petModelRequest = petModelRequest;
        isCustomRequestMode = true;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Post.to(ADD_NEW_PET_TO_THE_STORE.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                                .body(defineBodyRequestMode())
                        )
        );
    }

    private String defineBodyRequestMode() {
        return isCustomRequestMode ? generateJson(petModelRequest) : generateJson(defaultPetModelRequestObject());
    }

    public static AddNewPet ToTheStore() {
        return new AddNewPet();
    }
}
