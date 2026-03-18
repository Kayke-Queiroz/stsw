package com.example.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
// Indica onde estão os arquivos .feature
@SelectClasspathResource("features")
// Indica onde estão os pacotes com as anotações @Dado, @Quando, @Então (o
// pacote steps)
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.example.steps")
public class CucumberTest {
}