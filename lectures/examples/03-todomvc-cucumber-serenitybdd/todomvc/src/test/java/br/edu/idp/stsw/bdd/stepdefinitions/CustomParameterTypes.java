package br.edu.idp.stsw.bdd.stepdefinitions;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class CustomParameterTypes {

    @ParameterType(".*") // Matches any character sequence for 'actor'
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName); // Simple return, or perform transformations here
    }
}