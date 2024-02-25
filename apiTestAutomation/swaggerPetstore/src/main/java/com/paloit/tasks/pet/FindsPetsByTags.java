package com.paloit.tasks.pet;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Collections;
import java.util.List;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.pet.PetResources.FIND_PETS_BY_TAGS;

public class FindsPetsByTags implements Task {
    private static final String TAGS = "tags";
    private final List<String> tag;

    public FindsPetsByTags(List<String> tag) {
        this.tag = tag;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Get.resource(FIND_PETS_BY_TAGS.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                                .params(Collections.singletonMap(TAGS, tag))
                        )
        );
    }

    public static FindsPetsByTags suchAs(List<String> tag) {
        return new FindsPetsByTags(tag);
    }
}
