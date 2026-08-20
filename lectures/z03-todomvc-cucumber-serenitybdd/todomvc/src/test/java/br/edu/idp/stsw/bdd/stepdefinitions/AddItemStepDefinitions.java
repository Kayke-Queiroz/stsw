package br.edu.idp.stsw.bdd.stepdefinitions;

import org.openqa.selenium.WebDriver;

import br.edu.idp.stsw.bdd.helpers.AddAnItem;
import br.edu.idp.stsw.bdd.helpers.NavigateTo;
import br.edu.idp.stsw.bdd.helpers.TodoListPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;


public class AddItemStepDefinitions {

    @Managed
    WebDriver hisBrowser;

    @Before
    public void configTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("{actor} is looking at his TODO list")    
    public void actor_is_looking_at_his_todo_list(Actor actor) {        
        //Actor actor = OnStage.theActorCalled(actorName);
        actor.can(BrowseTheWeb.with(hisBrowser));
        actor.wasAbleTo(NavigateTo.theTodoListPage());
    }    
    
    @When("{actor} adds {string} to the list")    
    public void he_adds_to_the_list(Actor actor, String itemName) {        
        //Actor actor = OnStage.theActorCalled(actorName);
        actor.can(BrowseTheWeb.with(hisBrowser));
        actor.attemptsTo(AddAnItem.withName(itemName));
    }
    
    @Then("{actor} sees {string} as an item in the TODO list")    
    public void he_sees_as_an_item_in_the_todo_list(Actor actor, String expectedItemName) {        
        //Actor actor = OnStage.theActorCalled(actorName);        
        actor.can(BrowseTheWeb.with(hisBrowser));
        actor.attemptsTo(Ensure.that(TodoListPage.ITEMS_LIST).hasText(expectedItemName));
    }
}
