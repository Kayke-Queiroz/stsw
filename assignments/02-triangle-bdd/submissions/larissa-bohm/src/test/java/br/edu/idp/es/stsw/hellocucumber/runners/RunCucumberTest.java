package br.edu.idp.es.stsw.hellocucumber.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"br.edu.idp.es.stsw.hellocucumber.steps"},
    plugin = {"pretty"},
    monochrome = true
)
public class RunCucumberTest { }
