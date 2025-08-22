package br.edu.idp.es.stsw.hellocucumber.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features") // procura em src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "br.edu.idp.es.stsw.hellocucumber.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest { }
