package com.paloit.tasks.store;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Collections;

import static com.paloit.utils.ContentType.APPLICATION_JSON;
import static com.paloit.utils.apiresources.StoreResources.STORE_INVENTORY;

public class GetAllTheInventory implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Get.resource(STORE_INVENTORY.getValue())
                        .with(request -> request
                                .headers(Collections.singletonMap(APPLICATION_JSON.getKey(), APPLICATION_JSON.getMimeType()))
                )
        );
    }

    public static GetAllTheInventory untilNow() {
        return new GetAllTheInventory();
    }
}
