package com.paloit.stepdefinitions.configuration;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SetUp {
    protected static final String ACTOR_NAME = "Iván";
    protected static final String HTTP_STATUS_CODE_MESSAGE = "The HTTP/HTTPS status code should be ";
    protected static final String URL_BASE = "http://localhost:8080";

    private void setUpActor() {
        OnStage.setTheStage(new OnlineCast());
    }

    private void setUpActorAndApi(String actorName){
        OnStage.theActor(actorName).can(CallAnApi.at(URL_BASE));
    }

    protected void setUp(String actorName){
        setUpActor();
        setUpActorAndApi(actorName);
    }

    public HashMap<String, Object> headers(){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    protected String fromLastResponseBy(Actor actor){
        return  new String(
                LastResponse.received().answeredBy(actor).asByteArray(),
                StandardCharsets.UTF_8
        );
    }
}
